package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import data.entidades.*;
import remote.IRemoteFacade;
import rmi.server.api.*;
import rmi.server.exceptions.InvalidUser;
import service.StaffService;

public class ServerStaff extends UnicastRemoteObject implements IStaffService {
    private static final long serialVersionUID = 1L;
    private StaffService staffService;

    private ServerStaff() throws RemoteException {
        super();
        staffService = new StaffService();
    }

    public static ServerStaff getInstance() throws RemoteException {
        return new ServerStaff();
    }

    public String sayHello() throws RemoteException {
        return staffService.sayHello();
    }

    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        return staffService.sayMessage(login, password, message);
    }

    @Override
    public Staff loginStaff(String login, String password) throws RemoteException {
        return staffService.loginStaff(login, password);
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
