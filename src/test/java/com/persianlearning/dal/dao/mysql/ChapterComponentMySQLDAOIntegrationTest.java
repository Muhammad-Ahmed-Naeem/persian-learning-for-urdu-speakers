package com.persianlearning.dal.dao.mysql;

import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.ComponentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/** Requires the configured MySQL schema; removes only its own UUID-based fixtures. */
@Tag("integration")
public class ChapterComponentMySQLDAOIntegrationTest {

    private Connection fixtureConnection;
    private final List<String> fixtureChapterIds = new ArrayList<>();
    private ChapterComponentMySQLDAO dao;
    private String chapterId;

    @BeforeEach
    public void setUp() throws SQLException {
        fixtureConnection = MySQLConnectionFactory.getConnection();
        dao = new ChapterComponentMySQLDAO();
        chapterId = createChapter();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (fixtureConnection != null) {
            try (Connection connection = fixtureConnection;
                 PreparedStatement delete = connection.prepareStatement(
                         "DELETE FROM chapter WHERE chapter_id = ?")) {
                for (String id : fixtureChapterIds) {
                    delete.setString(1, id);
                    delete.executeUpdate();
                }
            }
        }
    }

    @Test
    public void loadsAllTypesInSavedOrderAndPreservesUnicodeContent() throws SQLException {
        ComponentType[] types = ComponentType.values();
        List<String> componentIds = new ArrayList<>();
        String unicodeContent = "سلام، فارسی — اردو وضاحت 🌷";
        for (int index = 0; index < types.length; index++) {
            componentIds.add(UUID.randomUUID().toString());
        }
        // Insert in reverse order so the test depends on the SQL ORDER BY clause.
        for (int index = types.length - 1; index >= 0; index--) {
            insertComponent(componentIds.get(index), chapterId, types[index], index,
                    index == 0 ? unicodeContent : "");
        }

        List<ChapterComponent> components = dao.getComponentsByChapterId(chapterId);

        assertEquals(types.length, components.size());
        for (int index = 0; index < types.length; index++) {
            ChapterComponent component = components.get(index);
            assertEquals(componentIds.get(index), component.getComponentId());
            assertEquals(chapterId, component.getChapterId());
            assertEquals(types[index], component.getType());
            assertEquals(index, component.getOrderIndex());
            assertEquals(index == 0 ? unicodeContent : "", component.getContent());
        }
    }

    @Test
    public void existingChapterWithoutStructureReturnsEmptyList() {
        assertTrue(dao.getComponentsByChapterId(chapterId).isEmpty());
    }

    @Test
    public void unknownChapterReturnsEmptyList() {
        assertTrue(dao.getComponentsByChapterId(UUID.randomUUID().toString()).isEmpty());
    }

    @Test
    public void readsOnlyTheRequestedChapter() throws SQLException {
        String otherChapterId = createChapter();
        String componentId = UUID.randomUUID().toString();
        insertComponent(componentId, chapterId, ComponentType.GLOSSARY, 0, "");
        insertComponent(UUID.randomUUID().toString(), otherChapterId, ComponentType.EXAMPLES, 0, "Other chapter");

        List<ChapterComponent> components = dao.getComponentsByChapterId(chapterId);

        assertEquals(1, components.size());
        assertEquals(componentId, components.get(0).getComponentId());
        assertEquals(chapterId, components.get(0).getChapterId());
    }

    private String createChapter() throws SQLException {
        String id = UUID.randomUUID().toString();
        fixtureChapterIds.add(id);
        try (PreparedStatement insert = fixtureConnection.prepareStatement(
                "INSERT INTO chapter (chapter_id, title) VALUES (?, ?)")) {
            insert.setString(1, id);
            insert.setString(2, "UC-02 integration " + id);
            insert.executeUpdate();
        }
        return id;
    }

    private void insertComponent(String componentId, String ownerId, ComponentType type,
                                 int orderIndex, String content) throws SQLException {
        String sql = "INSERT INTO chapter_component "
                + "(component_id, chapter_id, component_type, order_index, content) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insert = fixtureConnection.prepareStatement(sql)) {
            insert.setString(1, componentId);
            insert.setString(2, ownerId);
            insert.setString(3, type.name());
            insert.setInt(4, orderIndex);
            insert.setString(5, content);
            insert.executeUpdate();
        }
    }
}
