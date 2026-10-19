package com.persianlearning.dal.dao.mysql;

import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.dal.exception.DataAccessException;
import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.ComponentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChapterComponentMySQLDAO implements IChapterComponentDAO {

    protected Connection getConnection() throws SQLException {
        return MySQLConnectionFactory.getConnection();
    }

    @Override
    public boolean saveStructure(String chapterId, List<ChapterComponent> components) {
        // TODO: MySQL save chapter structure
        return false;
    }

    @Override
    public List<ChapterComponent> getComponentsByChapterId(String chapterId) {
        if (chapterId == null || chapterId.trim().isEmpty()) {
            throw new IllegalArgumentException("Chapter ID is required.");
        }

        String sql = "SELECT component_id, chapter_id, component_type, order_index, content "
                + "FROM chapter_component WHERE chapter_id = ? ORDER BY order_index ASC";
        List<ChapterComponent> components = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chapterId);
            try (ResultSet rows = statement.executeQuery()) {
                while (rows.next()) {
                    components.add(readComponent(rows));
                }
            }
            return components;
        } catch (SQLException e) {
            throw new DataAccessException("Unable to load the chapter structure.", e);
        }
    }

    private ChapterComponent readComponent(ResultSet rows) throws SQLException {
        String typeName = rows.getString("component_type");
        ComponentType type;
        try {
            type = ComponentType.valueOf(typeName);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new SQLException("The saved structure contains an unsupported component type.", e);
        }

        return new ChapterComponent(
                rows.getString("component_id"),
                rows.getString("chapter_id"),
                type,
                rows.getInt("order_index"),
                rows.getString("content"));
    }

    @Override
    public ChapterComponent getComponentById(String id) {
        // TODO: MySQL get component by id
        return null;
    }

    @Override
    public boolean updateComponentContent(String id, String content) {
        // TODO: MySQL update content
        return false;
    }

    @Override
    public boolean deleteComponent(String id) {
        // TODO: MySQL delete component
        return false;
    }

    @Override
    public boolean deleteComponentsByChapterId(String chapterId) {
        return true;
    }
}
