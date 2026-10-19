package com.persianlearning.dal.dao.mysql;

import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.model.Chapter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChapterMySQLDAO implements IChapterDAO {

    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    static {
        // Read database configuration
        dbUrl = System.getenv("PERSIAN_DB_URL");
        dbUser = System.getenv("PERSIAN_DB_USER");
        dbPassword = System.getenv("PERSIAN_DB_PASSWORD");

        if (dbUrl == null || dbUser == null) {
            try {
                Properties props = new Properties();
                InputStream in = ChapterMySQLDAO.class.getClassLoader().getResourceAsStream("config.properties");
                if (in != null) {
                    props.load(new InputStreamReader(in, StandardCharsets.UTF_8));
                    in.close();
                }
                if (dbUrl == null) {
                    dbUrl = props.getProperty("db.url");
                }
                if (dbUser == null) {
                    dbUser = props.getProperty("db.username");
                }
                if (dbPassword == null) {
                    dbPassword = props.getProperty("db.password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Fallback default local settings
        if (dbUrl == null) {
            dbUrl = "jdbc:mysql://localhost:3306/persian_learning?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        }
        if (dbUser == null) {
            dbUser = "root";
        }
        if (dbPassword == null) {
            dbPassword = "admin";
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public boolean addChapter(Chapter chapter) {
        if (chapter == null || chapter.getChapterId() == null || chapter.getTitle() == null) {
            return false;
        }

        String sql = "INSERT INTO chapter (chapter_id, title) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chapter.getChapterId());
            stmt.setString(2, chapter.getTitle());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Chapter getChapterById(String id) {
        if (id == null) {
            return null;
        }

        String sql = "SELECT chapter_id, title FROM chapter WHERE chapter_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Chapter(rs.getString("chapter_id"), rs.getString("title"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Chapter> getAllChapters() {
        List<Chapter> chapters = new ArrayList<>();
        String sql = "SELECT chapter_id, title FROM chapter ORDER BY title ASC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                chapters.add(new Chapter(rs.getString("chapter_id"), rs.getString("title")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    @Override
    public boolean updateChapter(Chapter chapter) {
        if (chapter == null || chapter.getChapterId() == null || chapter.getTitle() == null) {
            return false;
        }

        String sql = "UPDATE chapter SET title = ? WHERE chapter_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chapter.getTitle());
            stmt.setString(2, chapter.getChapterId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteChapter(String id) {
        if (id == null) {
            return false;
        }

        String sql = "DELETE FROM chapter WHERE chapter_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
