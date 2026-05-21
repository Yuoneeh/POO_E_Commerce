package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Ajuste a URL, usuário e senha de acordo com o seu MySQL
                String url = "jdbc:mysql://localhost:3306/teste";
                conn = DriverManager.getConnection(url, "root", "");
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return conn;
    }
}