package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/alexandria";
    private static final String USER = "your_postgres_user"; // substitua pelo seu usuário do PostgreSQL
    private static final String PASSWORD = "your_postgres_password"; // substitua pela sua senha do PostgreSQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
