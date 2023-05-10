package controller;

import remote.IRemoteFacade;
import data.entidades.User;
import remote.ServiceLocator;
import java.rmi.RemoteException;
import rmi.server.exceptions.InvalidUser;

public class UserController {
    private IRemoteFacade remoteFacade;

    public UserController(ServiceLocator serviceLocator) {
        this.remoteFacade = serviceLocator.getRemoteFacade();
    }

    public User loginUser(String login, String password) {
        try {
            User user = remoteFacade.loginUser(login, password);
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

    public User registerUser(String dni, String name, String surname, String email, String password) {
        try {
            User newUser = remoteFacade.registerUser(dni, name, surname, email, password);
            if (newUser == null) {
                System.out.println("UserController: User registration failed.");
            } else {
                System.out.println("UserController: User registration successful.");
            }
            return newUser;
        } catch (RemoteException | InvalidUser e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public IRemoteFacade getRemoteFacade() {
        return remoteFacade;
    }
}

