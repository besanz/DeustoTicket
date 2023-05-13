package remote.impl;

import data.entidades.*;
import rmi.remote.api.IStaffService;
import rmi.remote.api.IUserService;
import rmi.server.exceptions.InvalidUser;
import remote.IRemoteFacade;
import remote.service.StaffService;
import remote.service.UserService;

import java.rmi.RemoteException;
import java.util.List;

public class Remote implements IRemoteFacade {
    private static Remote instance;
    private IStaffService staffService;
    private IUserService userService;

    private Remote() throws RemoteException {
        staffService = StaffService.getInstance();
        userService = UserService.getInstance();
    }

    public static Remote getInstance() throws RemoteException {
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
    public List<Artista> obtenerArtistas() throws RemoteException {
        return userService.obtenerArtistas();
    }

    @Override
    public List<Evento> obtenerEventos() throws RemoteException {
        return userService.obtenerEventos();
    }

    @Override
    public Evento obtenerEventoPorID(int id) throws RemoteException {
        return userService.obtenerEventoPorID(id);
    }

    @Override
    public List<Evento> obtenerEventosDestacados() throws RemoteException {
        return userService.obtenerEventosDestacados();
    }

    @Override
    public List<Espacio> obtenerEspaciosDeEvento(int eventoId) throws RemoteException {
        return userService.obtenerEspaciosDeEvento(eventoId);
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
