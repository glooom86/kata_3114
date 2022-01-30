package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "8462fender";

    public static void main(String[] args) {

        /*try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            if (!connection.isClosed()){
                System.out.println("Соединение с БД установлено");
            }
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO mynewdatabase.animals(anim_name, anim_desc) VALUES('MOOSE','LOVE SALT')");
            connection.close();
            if (connection.isClosed()){
                System.out.println("Соединение с БД закрыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        /*try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            if (!connection.isClosed()) {
                System.out.println("Соединение с базой установлено");
            }
            statement.execute("INSERT INTO animals(anim_name, anim_desc) VALUES('name', 'desc');");

                //Запросы типа INSERT, UPDATE, DELETE
            int res = statement.executeUpdate("UPDATE animals SET anim_name = 'zhivotnoe' where id = 1");
            System.out.println(res);

                //Запросы типа SELECT. Возвращает ResultSet, который хз что такое
                statement.executeQuery("SELECT * FROM animals");

                //Пакетная обработка запроса
                //Добавление в бэтч запросов
            statement.addBatch("INSERT INTO animals(anim_name, anim_desc) VALUES ('KOROVA', 'MUMU BITCH!')");
            statement.addBatch("INSERT INTO animals(anim_name, anim_desc) VALUES ('SOBAKA', 'WUF WUF')");
            statement.addBatch("INSERT INTO animals(anim_name, anim_desc) VALUES ('LOSHADKA', 'IGOGO')");
                //Выполнение бэтча
            statement.executeBatch();
                //Очистка бэтча
            statement.clearBatch();


        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        UserServiceImpl userService = new UserServiceImpl();
        //userService.createUsersTable();
//        userService.saveUser("Motya", "Kilka", (byte) 32);
//        userService.saveUser("Vasya", "Yaof", (byte) 27);
//        userService.saveUser("Abdurahim", "Maga-ogli", (byte) 64);
        userService.cleanUsersTable();
        List<User> users = userService.getAllUsers();
        for (User user: users) {
            System.out.println(user);
        }


        //userService.dropUsersTable();
        //userService.removeUserById(1);
    }
}
