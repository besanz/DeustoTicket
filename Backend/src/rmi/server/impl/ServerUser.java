package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.jdo.*;

import data.entidades.*;
import remote.IRemoteFacade;
import rmi.server.api.*;
import rmi.server.exceptions.InvalidUser;
import service.UserService;

public class ServerUser extends UnicastRemoteObject implements IRemoteFacade {
    private static ServerUser instance;
    private IUserService userService;

    private ServerUser() throws RemoteException {
        userService = UserService.getInstance();
    }

    public static ServerUser getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ServerUser();
        }
        return instance;
    }

    public User loginUser(String login, String password) throws RemoteException {
        User user = userService.loginUser(login, password);
        if (user != null) {
            System.out.println("User " + login + " has logged in.");
        }
        return user;
    }

    @Override
    public Staff loginStaff(String login, String password) throws RemoteException {
        System.out.println("No es posible aqui");
        return null;
    }

    public String sayHello() throws RemoteException {
        return userService.sayHello();
    }

    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        return userService.sayMessage(login, password, message);
    }

    public void registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException, InvalidUser {
        userService.registerUser(dni, nombre, apellidos, email, password);
        System.out.println("User " + email + " has registered.");
    }


    public static void main(String[] args) {
        try {

            String host = "127.0.0.1";
            int port = 2000;
            String serverName = "GuTicketServer";

            System.setProperty("java.security.policy", "../security/java.policy");

            System.setProperty("java.rmi.server.hostname", host);
            IRemoteFacade objServer = ServerUser.getInstance();

            Registry registry = LocateRegistry.createRegistry(port);
            System.out.println("RMI Registry created on port " + port);

            registry.rebind(serverName, objServer);
            System.out.println("* Server '" + "//" + host + ":" + port + "/" + serverName + "' active and waiting...");
    
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

