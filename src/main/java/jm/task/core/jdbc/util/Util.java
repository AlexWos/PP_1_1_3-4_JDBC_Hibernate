package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class Util {

    private final String URL = "jdbc:mysql://localhost/lesson";
    private final String USER = "root";
    private final String PASSWORD = "112233";
    Connection conn;
    public void connectionDb() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public void close()  {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void sendSql(String sql) {
        try {
            Statement state = conn.createStatement();
            state.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet executeQuery() {
        try {
            Statement state = conn.createStatement();
            return state.executeQuery("SELECT * FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateSQL (String sql) {
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
