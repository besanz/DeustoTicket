package service;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.IUserDAO;
import data.dao.impl.UserDAO;
import data.entidades.*;
import rmi.server.exceptions.InvalidUser;
import rmi.server.api.IUserService;

public class UserService implements IUserService {
    private UserDAO userDAO;
    private static UserService instance;

    public UserService() {
        userDAO = UserDAO.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User loginUser(String login, String password) throws RemoteException {
        User user = userDAO.findByLoginAndPassword(login, password);
        if (user == null) {
            System.out.println("UserService: User not found in the database.");
        } else {
            System.out.println("UserService: User found in the database.");
        }
        return user;
    }

    public String sayHello() throws RemoteException {
        return "Hello!";
    }

    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        return "Hello, " + login + "! Your message is: " + message;
    }

    @Override
    public void registrarUsuario(String login, String password) throws RemoteException, InvalidUser {
        // Implementa la lógica para registrar un usuario aquí
    }

    @Override
    public boolean validarUsuario(String login, String password) throws RemoteException, InvalidUser {
        // Implementa la lógica para validar un usuario aquí
        return false;
    }

    @Override
    public void registrarCliente(String login, String password) throws RemoteException, InvalidUser {
        // Implementa la lógica para registrar un cliente aquí
    }

    @Override
    public Cliente crearCliente(String nombre) throws RemoteException {
        // Implementa la lógica para crear un cliente aquí
        return null;
    }

    @Override
    public List<Cliente> obtenerClientes() throws RemoteException {
        // Implementa la lógica para obtener clientes aquí
        return null;
    }

    @Override
    public Cliente actualizarCliente(int id, String nombre) throws RemoteException {
        // Implementa la lógica para actualizar un cliente aquí
        return null;
    }

    @Override
    public boolean eliminarCliente(int id) throws RemoteException {
        // Implementa la lógica para eliminar un cliente aquí
        return false;
    }

    @Override
    public List<Artista> obtenerArtistas() throws RemoteException {
        // Implementa la lógica para obtener artistas aquí
        return null;
    }

    @Override
    public List<Evento> obtenerEventos() throws RemoteException {
        // Implementa la lógica para obtener eventos aquí
        return null;
    }

    @Override
    public Evento obtenerEventoPorID(int id) throws RemoteException {
        // Implementa la lógica para obtener un evento por ID aquí
        return null;
    }

    @Override
    public List<Evento> obtenerEventosDestacados() throws RemoteException {
        // Implementa la lógica para obtener eventos destacados aquí
        return null;
    }
}
