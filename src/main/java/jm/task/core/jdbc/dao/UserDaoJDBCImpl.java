package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    private String createTable = "CREATE TABLE mynewdatabase.users (" +
            "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            "name VARCHAR(45)," +
            "lastName VARCHAR(45)," +
            "age INT NOT NULL);";
    private String dropTable = "DROP TABLE mynewdatabase.users";


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = util.getStatement();
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            System.err.println("Таблица с таким именем уже существует");;
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = util.getStatement();
            statement.executeUpdate(dropTable);
        } catch (SQLException e) {
            System.err.println("Такой таблицы не существует");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = util.getStatement();
            statement.executeUpdate("INSERT INTO mynewdatabase.users(name, lastName, age) VALUES('" + name + "','" + lastName + "','" + age + "');");
        } catch (SQLException e) {
            System.err.println("Какая то ошибка при добавлении пользователя");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = util.getStatement();
            statement.executeUpdate("DELETE FROM mynewdatabase.users WHERE id=" + id + ";");
        } catch (SQLException e) {
            System.err.println("Какая то ошибка при удалении пользователя");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
            try {
                Statement statement = util.getStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM mynewdatabase.users");
                while (resultSet.next()){
                    User user = new User();
                    user.setId((long) resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    users.add(user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return users;
    }

    public void cleanUsersTable() {
        try{
            Statement statement = util.getStatement();
            statement.executeUpdate("TRUNCATE mynewdatabase.users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
