package remote.service;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.IUserDAO;
import data.dao.impl.UserDAO;
import data.entidades.*;
import rmi.server.exceptions.InvalidUser;
import rmi.remote.api.IUserService;
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
        try {
            return ticketProviderClient.getEventoById(id);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener el evento por ID de la API", e);
        }
    }

    @Override
    public List<Evento> obtenerEventosDestacados() throws RemoteException {
        try {
            return ticketProviderClient.getEventos(); // Utiliza el mismo método getEventos para obtener eventos destacados
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener eventos destacados de la API", e);
        }
    }

    @Override
    public List<Espacio> obtenerEspaciosDeEvento(int eventoId) throws RemoteException {
        try {
            return ticketProviderClient.getEspaciosByEventoId(eventoId);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener espacios de la API", e);
        }
    }

}
