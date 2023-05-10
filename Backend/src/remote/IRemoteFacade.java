package remote;

import data.entidades.Artista;
import data.entidades.Evento;
import data.entidades.Staff;
import data.entidades.User;

import rmi.server.exceptions.InvalidUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteFacade extends Remote {
    User loginUser(String login, String password) throws RemoteException;
    User registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException, InvalidUser;
    Staff loginStaff(String login, String password) throws RemoteException;
    String sayHello() throws RemoteException;
    String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser;
    List<Artista> obtenerArtistas() throws RemoteException;
    List<Evento> obtenerEventos() throws RemoteException;
    Evento obtenerEventoPorID(int id) throws RemoteException;
    List<Evento> obtenerEventosDestacados() throws RemoteException;
}

