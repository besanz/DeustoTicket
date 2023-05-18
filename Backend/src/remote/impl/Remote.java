package remote.impl;

import data.entidades.*;
import remote.api.IStaffService;
import remote.api.IUserService;
import rmi.server.exceptions.InvalidUser;
import remote.IRemoteFacade;
import remote.service.StaffService;
import remote.service.UserService;
import remote.rest.gateway.TicketProviderClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import java.util.List;

public class Remote extends UnicastRemoteObject implements IRemoteFacade {
    private static Remote instance;
    private IStaffService staffService;
    private IUserService userService;
    private TicketProviderClient ticketProviderClient;

    private Remote() throws RemoteException {
        staffService = StaffService.getInstance();
        userService = UserService.getInstance();
        ticketProviderClient = TicketProviderClient.getInstance(); // Utilizar la instancia singleton
    }

    public static synchronized Remote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new Remote();
        }
        return instance;
    }
    
    @Override
    public User loginUser(String login, String password) throws RemoteException {
        User user = userService.loginUser(login, password);
        if (user != null) {
            System.out.println("User " + login + " has logged in.");
        }
        return user;
    }

    @Override
    public User registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException, InvalidUser {
        User newUser = userService.registerUser(dni, nombre, apellidos, email, password);
        System.out.println("User " + email + " has registered.");
        return newUser;
    }

    @Override
    public Staff registerStaff(String username, String password) throws RemoteException, InvalidUser {
        Staff newStaff = staffService.registerStaff(username, password);
        System.out.println("Staff " + username + " has registered.");
        return newStaff;
    }

    @Override
    public Staff loginStaff(String login, String password) throws RemoteException {
        return staffService.loginStaff(login, password);
    }

    @Override
    public List<Artista> obtenerArtistas(int eventoID) throws RemoteException {
        return userService.obtenerArtistas(eventoID);
    }

    @Override
    public Artista obtenerArtistaPorID(int artistaID) throws RemoteException {
        return userService.obtenerArtistaPorID(artistaID);
    }

    @Override
    public List<Evento> obtenerEventos() throws RemoteException {
        return userService.obtenerEventos();
    }

    @Override
    public Espacio obtenerEspacioDeEvento(int eventoID) throws RemoteException {
        return userService.obtenerEspacioDeEvento(eventoID);
    }

    @Override
    public List<User> findAllUsers() throws RemoteException {
        return staffService.findAllUsers();
    }

    @Override
    public void deleteUserByDni(String dni) throws RemoteException {
        staffService.deleteUserByDni(dni);
    }
}
