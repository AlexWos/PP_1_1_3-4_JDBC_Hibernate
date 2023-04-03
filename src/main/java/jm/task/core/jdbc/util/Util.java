package jm.task.core.jdbc.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class Util {

    private final String URL = "jdbc:mysql://localhost/lesson";
    private final String USER = "root";
    private final String PASSWORD = "112233";
    private static Connection conn = null;
    private static Util instance = null;

    private Util() {
        try {
            if (null == conn || conn.isClosed()) {
                conn = DriverManager
                        .getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
