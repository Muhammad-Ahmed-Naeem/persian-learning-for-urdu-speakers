package com.persianlearning.dal.dao.mysql;

import com.persianlearning.dal.exception.DataAccessException;
import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.ComponentType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChapterComponentMySQLDAOTest {

    @Test
    public void loadsAllFieldsAndBindsChapterInOrderQuery() {
        JdbcReadStub jdbc = new JdbcReadStub(new Object[][] {
                {"component-1", "chapter-1", "PERSIAN_DIALOGUE", 0, "سلام — اردو وضاحت"},
                {"component-2", "chapter-1", "GLOSSARY", 1, ""}
        });

        List<ChapterComponent> components = daoUsing(jdbc).getComponentsByChapterId("chapter-1");

        assertEquals(2, components.size());
        ChapterComponent first = components.get(0);
        assertEquals("component-1", first.getComponentId());
        assertEquals("chapter-1", first.getChapterId());
        assertEquals(ComponentType.PERSIAN_DIALOGUE, first.getType());
        assertEquals(0, first.getOrderIndex());
        assertEquals("سلام — اردو وضاحت", first.getContent());
        assertEquals(ComponentType.GLOSSARY, components.get(1).getType());
        assertEquals(1, components.get(1).getOrderIndex());
        assertEquals("", components.get(1).getContent());
        assertTrue(jdbc.sql.contains("WHERE chapter_id = ? ORDER BY order_index ASC"));
        assertEquals(1, jdbc.parameterIndex);
        assertEquals("chapter-1", jdbc.chapterId);
        assertAllResourcesClosed(jdbc);
    }

    @Test
    public void noMatchingRowsReturnsEmptyList() {
        JdbcReadStub jdbc = new JdbcReadStub(new Object[0][]);

        assertTrue(daoUsing(jdbc).getComponentsByChapterId("chapter-1").isEmpty());

        assertAllResourcesClosed(jdbc);
    }

    @Test
    public void nullChapterIdIsRejectedBeforeOpeningConnection() {
        assertThrows(IllegalArgumentException.class,
                () -> unavailableDao().getComponentsByChapterId(null));
    }

    @Test
    public void blankChapterIdIsRejectedBeforeOpeningConnection() {
        assertThrows(IllegalArgumentException.class,
                () -> unavailableDao().getComponentsByChapterId(" \t "));
    }

    @Test
    public void connectionFailureIsNotReportedAsAnEmptyStructure() {
        DataAccessException failure = assertThrows(DataAccessException.class,
                () -> unavailableDao().getComponentsByChapterId("chapter-1"));

        assertEquals("Unable to load the chapter structure.", failure.getMessage());
        assertTrue(failure.getCause() instanceof SQLException);
    }

    @Test
    public void statementCreationFailureClosesConnection() {
        JdbcReadStub jdbc = new JdbcReadStub(new Object[0][]);
        jdbc.prepareFailure = new SQLException("Test statement failure");

        DataAccessException failure = assertThrows(DataAccessException.class,
                () -> daoUsing(jdbc).getComponentsByChapterId("chapter-1"));

        assertSame(jdbc.prepareFailure, failure.getCause());
        assertTrue(jdbc.connectionClosed);
    }

    @Test
    public void queryFailureClosesStatementAndConnection() {
        JdbcReadStub jdbc = new JdbcReadStub(new Object[0][]);
        jdbc.queryFailure = new SQLException("Test query failure");

        DataAccessException failure = assertThrows(DataAccessException.class,
                () -> daoUsing(jdbc).getComponentsByChapterId("chapter-1"));

        assertSame(jdbc.queryFailure, failure.getCause());
        assertTrue(jdbc.statementClosed);
        assertTrue(jdbc.connectionClosed);
    }

    @Test
    public void midReadFailureDoesNotReturnPartialStructure() {
        JdbcReadStub jdbc = new JdbcReadStub(new Object[][] {
                {"component-1", "chapter-1", "EXAMPLES", 0, "Existing content"},
                {"component-2", "chapter-1", "GLOSSARY", 1, ""}
        });
        jdbc.failAtRow = 1;

        assertThrows(DataAccessException.class,
                () -> daoUsing(jdbc).getComponentsByChapterId("chapter-1"));

        assertAllResourcesClosed(jdbc);
    }

    @Test
    public void unsupportedStoredTypeIsReportedAsDataFailure() {
        assertInvalidStoredType("UNSUPPORTED_TYPE");
    }

    @Test
    public void missingStoredTypeIsReportedAsDataFailure() {
        assertInvalidStoredType(null);
    }

    private void assertInvalidStoredType(String type) {
        JdbcReadStub jdbc = new JdbcReadStub(new Object[][] {
                {"component-1", "chapter-1", type, 0, ""}
        });

        DataAccessException failure = assertThrows(DataAccessException.class,
                () -> daoUsing(jdbc).getComponentsByChapterId("chapter-1"));

        assertTrue(failure.getCause() instanceof SQLException);
        assertAllResourcesClosed(jdbc);
    }

    private ChapterComponentMySQLDAO daoUsing(final JdbcReadStub jdbc) {
        return new ChapterComponentMySQLDAO() {
            @Override
            protected Connection getConnection() {
                return jdbc.connection;
            }
        };
    }

    private ChapterComponentMySQLDAO unavailableDao() {
        return new ChapterComponentMySQLDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Test connection failure");
            }
        };
    }

    private void assertAllResourcesClosed(JdbcReadStub jdbc) {
        assertTrue(jdbc.rowsClosed);
        assertTrue(jdbc.statementClosed);
        assertTrue(jdbc.connectionClosed);
    }

    /** A small JDBC stub; no database or mocking framework is needed. */
    private static class JdbcReadStub {
        private final Connection connection;
        private String sql;
        private int parameterIndex;
        private String chapterId;
        private boolean rowsClosed;
        private boolean statementClosed;
        private boolean connectionClosed;
        private SQLException prepareFailure;
        private SQLException queryFailure;
        private int failAtRow = -1;
        private int rowIndex = -1;

        JdbcReadStub(final Object[][] data) {
            final List<String> columns = Arrays.asList(
                    "component_id", "chapter_id", "component_type", "order_index", "content");
            final ResultSet rows = proxy(ResultSet.class, (object, method, arguments) -> {
                switch (method.getName()) {
                    case "next":
                        rowIndex++;
                        if (rowIndex == failAtRow) {
                            throw new SQLException("Test row-read failure");
                        }
                        return rowIndex < data.length;
                    case "getString":
                    case "getInt":
                        int columnIndex = columns.indexOf(arguments[0]);
                        if (columnIndex < 0) {
                            throw new AssertionError("Unexpected column: " + arguments[0]);
                        }
                        return data[rowIndex][columnIndex];
                    case "close":
                        rowsClosed = true;
                        return null;
                    default:
                        throw new AssertionError("Unexpected ResultSet call: " + method.getName());
                }
            });
            final PreparedStatement statement = proxy(PreparedStatement.class, (object, method, arguments) -> {
                switch (method.getName()) {
                    case "setString":
                        parameterIndex = (Integer) arguments[0];
                        chapterId = (String) arguments[1];
                        return null;
                    case "executeQuery":
                        if (queryFailure != null) {
                            throw queryFailure;
                        }
                        return rows;
                    case "close":
                        statementClosed = true;
                        return null;
                    default:
                        throw new AssertionError("Unexpected statement call: " + method.getName());
                }
            });
            connection = proxy(Connection.class, (object, method, arguments) -> {
                switch (method.getName()) {
                    case "prepareStatement":
                        if (prepareFailure != null) {
                            throw prepareFailure;
                        }
                        sql = (String) arguments[0];
                        return statement;
                    case "close":
                        connectionClosed = true;
                        return null;
                    default:
                        throw new AssertionError("Unexpected connection call: " + method.getName());
                }
            });
        }

        private static <T> T proxy(Class<T> type, InvocationHandler handler) {
            return type.cast(Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] {type}, handler));
        }
    }
}
