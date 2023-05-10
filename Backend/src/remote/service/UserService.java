package remote.service;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.IUserDAO;
import data.dao.impl.UserDAO;
import data.entidades.*;
import rmi.server.exceptions.InvalidUser;
import rmi.server.api.IUserService;
import remote.rest.TicketProviderClient;
import java.io.IOException;


public class UserService implements IUserService {
    private static UserService instance;
    private final UserDAO userDAO;
    private final TicketProviderClient ticketProviderClient;

    private UserService() {
        this.userDAO = UserDAO.getInstance();
        this.ticketProviderClient = new TicketProviderClient();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public String sayHello() throws RemoteException {
        return "Hello!";
    }

    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        return "Hello, " + login + "! Your message is: " + message;
    }

    public User loginUser(String login, String password) {
        return userDAO.findByLoginAndPassword(login, password);
    }

    public User registerUser(String dni, String name, String surname, String email, String password) throws InvalidUser {
        if (userDAO.findByEmail(email) != null) {
            throw new InvalidUser("Email is already registered.");
        }
        User newUser = new User(dni, name, surname, email, password);
        userDAO.addUser(newUser);
        return newUser;
    }

    @Override
    public List<Artista> obtenerArtistas() throws RemoteException {
        try {
            return ticketProviderClient.getArtistas();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener artistas de la API", e);
        }
    }

    @Override
    public List<Evento> obtenerEventos() throws RemoteException {
        try {
            return ticketProviderClient.getEventos();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener eventos de la API", e);
        }
    }

    @Override
    public Evento obtenerEventoPorID(int id) throws RemoteException {
        // Implementa la lógica para obtener un evento por ID utilizando la instancia de TicketProviderClient
        return null;
    }

    @Override
    public List<Evento> obtenerEventosDestacados() throws RemoteException {
        // Implementa la lógica para obtener eventos destacados utilizando la instancia de TicketProviderClient
        return null;
    }

}
