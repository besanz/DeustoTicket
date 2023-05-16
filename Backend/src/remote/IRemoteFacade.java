package remote;

import data.entidades.*;

import rmi.server.exceptions.InvalidUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteFacade extends Remote {

    User loginUser(String login, String password) throws RemoteException;

    User registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException, InvalidUser;

    Staff registerStaff(String username, String password) throws RemoteException, InvalidUser;

    Staff loginStaff(String login, String password) throws RemoteException;

    // Metodos para obtener datos de la API
    List<Artista> obtenerArtistas(int eventoID) throws RemoteException;

    Artista obtenerArtistaPorID(int artistaID) throws RemoteException;

    List<Evento> obtenerEventos() throws RemoteException;

    Evento obtenerEventoPorID(int eventoID) throws RemoteException;

    List<Evento> obtenerEventosDestacados() throws RemoteException;

    Espacio obtenerEspacioDeEvento(int eventoID) throws RemoteException;

    // Metodos que van al DAO
    List<User> findAllUsers() throws RemoteException;

    void deleteUserByDni(String dni) throws RemoteException;
}
