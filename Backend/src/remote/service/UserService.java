package remote.service;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.IUserDAO;
import data.dao.impl.UserDAO;
import data.entidades.*;
import rmi.server.exceptions.InvalidUser;
import remote.api.IUserService;
import remote.rest.gateway.TicketProviderClient;
import java.io.IOException;

public class UserService implements IUserService {
    private static UserService instance;
    private final IUserDAO userDAO;
    private final TicketProviderClient ticketProviderClient;

    private UserService() {
        this.userDAO = UserDAO.getInstance();
        this.ticketProviderClient = TicketProviderClient.getInstance();
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

    public User registerUser(String dni, String name, String surname, String email, String password)
            throws InvalidUser {
        if (userDAO.findByEmail(email) != null) {
            throw new InvalidUser("Email is already registered.");
        }
        User newUser = new User(dni, name, surname, email, password);
        userDAO.addUser(newUser);
        return newUser;
    }

    @Override
    public List<Artista> obtenerArtistas(int eventoID) throws RemoteException {
        try {
            return ticketProviderClient.getArtistasByEventoID(eventoID);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener artistas de la API", e);
        }
    }

    @Override
    public Artista obtenerArtistaPorID(int artistaID) throws RemoteException {
        try {
            return ticketProviderClient.getArtistaByID(artistaID);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener el artista por ID de la API", e);
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
    public List<Evento> obtenerEventosDestacados() throws RemoteException {
        try {
            return ticketProviderClient.getEventos(); // Utiliza el mismo método getEventos para obtener eventos
                                                      // destacados
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener eventos destacados de la API", e);
        }
    }

    @Override
    public Espacio obtenerEspacioDeEvento(int eventoID) throws RemoteException {
        try {
            return ticketProviderClient.getEspacioByEventoID(eventoID);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error al obtener espacios de la API", e);
        }
    }
}
