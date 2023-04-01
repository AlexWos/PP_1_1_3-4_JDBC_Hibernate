package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.beans.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class UserDaoJDBCImpl implements UserDao {
    private String tableName = "users";
    Util util = new Util();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try {
            util.connectionDb();
            util.sendSql("CREATE TABLE IF NOT EXISTS " + tableName + " (id LONG PRIMARY KEY AUTO_INCREMENT, name STRING, lastName STRING, age BYTE)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close();
        }
    }

    public void dropUsersTable() {
        try  {
            util.connectionDb();
            util.sendSql("DROP TABLE IF EXISTS " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            util.connectionDb();
            util.updateSQL("INSERT INTO " + tableName + " (name, lastName, age) Values (" + name + ", " + lastName + ", " + age + ")");
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close();
        }
    }

    public void removeUserById(long id) {
        try {
            util.connectionDb();
            util.updateSQL("DELETE FROM " + tableName + " WHERE id = " + id + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = util.executeQuery()) {
            util.connectionDb();
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            util.connectionDb();
            util.updateSQL("TRUNCATE TABLE " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close();
        }
    }
}
