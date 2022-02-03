package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Monya","Nikonorov",(byte) 32);
        userService.saveUser("Ponya","Taburetkin",(byte) 43);
        userService.saveUser("Slonya","Ivanova",(byte) 21);
        userService.saveUser("Fonya","Bond",(byte) 18);
        userService.removeUserById(2);
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
