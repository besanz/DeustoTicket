package controller;

import service.UserService;
import data.entidades.User;
import remote.ServiceLocator;
import java.rmi.RemoteException;

public class UserController {
    private UserService userService;

    public UserController(ServiceLocator serviceLocator) {
        this.userService = UserService.getInstance();
    }

    public User loginUser(String login, String password) {
        try {
            User user = userService.loginUser(login, password);
            if (user == null) {
                System.out.println("UserController: User not found in the database.");
            } else {
                System.out.println("UserController: User found in the database.");
            }
            return user;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
