package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import data.entidades.*;
import remote.IRemoteFacade;
import rmi.server.api.*;
import rmi.server.exceptions.InvalidUser;
import remote.service.StaffService;
import java.util.List;


public class ServerStaff extends UnicastRemoteObject implements IRemoteFacade {
    private static final long serialVersionUID = 1L;
    private IStaffService staffService;

    private static ServerStaff instance;

    private ServerStaff() throws RemoteException {
        staffService = StaffService.getInstance();
    }

    public static ServerStaff getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ServerStaff();
        }
        return instance;
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

    public Staff registerStaff(String username, String password) throws RemoteException, InvalidUser {
        Staff newStaff = staffService.registerStaff(username, password);
        System.out.println("Staff " + username + " has registered.");
        return newStaff;
    }
    
    @Override
    public List<Evento> obtenerEventosDestacados() throws RemoteException {
        
        return null;
    }
    
    @Override
    public Evento obtenerEventoPorID(int id) throws RemoteException {
        
        return null;
    }
    
    @Override
    public List<Evento> obtenerEventos() throws RemoteException {
        
        return null;
    }
    
    @Override
    public List<Artista> obtenerArtistas() throws RemoteException {
        
        return null;
    }
    @Override
    public User registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException {
        
        return null;
    }
    @Override
    public User loginUser(String login, String password) throws RemoteException{
        return null;
    }

    public static void main(String[] args) {
        try {
            String host = "127.0.0.1";
            int port = 1999;
            String serverName = "GuTicketServer";

            System.setProperty("java.security.policy", "../security/java.policy");

            System.setProperty("java.rmi.server.hostname", host);
            IRemoteFacade objServer = ServerStaff.getInstance();

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
