package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import data.entidades.User;
import remote.IRemoteFacade;
import rmi.server.api.IUserService;
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
        return userService.loginUser(login, password);
    }

    public String sayHello() throws RemoteException {
        return userService.sayHello();
    }

    public static void main(String[] args) {
        try {
            String host = "127.0.0.1";
            int port = 2000;
            String serverName = "GuTicketServer";

            //Establecer la política de seguridad para la conexión RMI
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

