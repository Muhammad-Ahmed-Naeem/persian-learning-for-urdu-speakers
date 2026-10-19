package com.persianlearning.dal.dao.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Opens JDBC connections using the application's existing configuration keys. */
public final class MySQLConnectionFactory {

    private MySQLConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        String url = System.getenv("PERSIAN_DB_URL");
        String username = System.getenv("PERSIAN_DB_USER");
        String password = System.getenv("PERSIAN_DB_PASSWORD");

        if (url == null || username == null || password == null) {
            Properties properties = new Properties();
            try (InputStream input = MySQLConnectionFactory.class.getClassLoader()
                    .getResourceAsStream("config.properties")) {
                if (input != null) {
                    properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
                }
            } catch (IOException | IllegalArgumentException e) {
                throw new SQLException("Unable to read database configuration.", e);
            }

            if (url == null) {
                url = properties.getProperty("db.url");
            }
            if (username == null) {
                username = properties.getProperty("db.username");
            }
            if (password == null) {
                password = properties.getProperty("db.password");
            }
        }

        if (url == null || url.trim().isEmpty()
                || username == null || username.trim().isEmpty() || password == null) {
            throw new SQLException("Database configuration requires db.url, db.username and db.password "
                    + "or the corresponding PERSIAN_DB environment variables.");
        }

        return DriverManager.getConnection(url, username, password);
    }
}
