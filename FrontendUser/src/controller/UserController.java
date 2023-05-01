package controller;

import service.UserService;
import data.entidades.User;
import remote.ServiceLocator;
import java.rmi.RemoteException;
import rmi.server.exceptions.InvalidUser;

public class UserController {
    private UserService userService;

    public UserController(ServiceLocator serviceLocator) {
        this.userService = UserService.getInstance();
    }

    public User loginUser(String login, String password) {
        User user = userService.loginUser(login, password);
        if (user == null) {
            System.out.println("UserController: User not found in the database.");
        } else {
            System.out.println("UserController: User found in the database.");
        }
        return user;
    }

    public User registerUser(String dni, String name, String surname, String email, String password) {
        try {
            User newUser = userService.registerUser(dni, name, surname, email, password);
            if (newUser == null) {
                System.out.println("UserController: User registration failed.");
            } else {
                System.out.println("UserController: User registration successful.");
            }
            return newUser;
        } catch (InvalidUser e) {
            e.printStackTrace();
            return null;
        }
    }
}
