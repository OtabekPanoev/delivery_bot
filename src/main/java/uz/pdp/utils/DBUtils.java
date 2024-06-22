package uz.pdp.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public interface DBUtils {
    String USER = "postgres";
    String PASSWORD = "root123";
    String DB = "delivery_bot";

    static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DB, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
