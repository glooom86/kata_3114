package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private String createTable = "CREATE TABLE mynewdatabase.users (" +
            "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            "name VARCHAR(45)," +
            "lastName VARCHAR(45)," +
            "age INT NOT NULL);";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null){
                try{
                    statement.close();
                } catch (SQLException ignored){}
            }
            if (connection != null){
                try{
                    connection.close();
                } catch (SQLException ignored){}
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE mynewdatabase.users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored){}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored){}
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO mynewdatabase.users(name, lastName, age) VALUES (?,?,?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – "+name+" добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException ignored){}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored){}
            }
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM mynewdatabase.users WHERE id=?;";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException ignored){}
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ignored){}
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
            try {
                connection = Util.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM mynewdatabase.users");
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
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException ignored){}
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException ignored){}
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ignored){}
                }
            }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE mynewdatabase.users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored){}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored){}
            }
        }
    }
}
