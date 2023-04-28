package controller;

import service.UserService;
import data.entidades.User;
import remote.ServiceLocator;
import java.rmi.RemoteException;

public class LoginController {
    private UserService userService;

    public LoginController(ServiceLocator serviceLocator) {
        this.userService = UserService.getInstance();
    }

    public User loginUser(String login, String password) {
        try {
            return userService.loginUser(login, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
