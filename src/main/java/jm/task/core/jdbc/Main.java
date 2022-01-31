package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Kolya","Nikonorov",(byte) 32);
        userService.saveUser("Fedya","Taburetkin",(byte) 43);
        userService.saveUser("Masha","Ivanova",(byte) 21);
        userService.saveUser("James","Bond",(byte) 18);
        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();
    }
}
