package rmi.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.entidades.*;
import rmi.server.exceptions.InvalidUser;

public interface IUserService extends Remote {
    String sayHello() throws RemoteException;
    String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser;
    User loginUser(String login, String password) throws RemoteException;

    
    void registrarUsuario(String login, String password) throws RemoteException, InvalidUser;
    boolean validarUsuario(String login, String password) throws RemoteException, InvalidUser;
    void registrarCliente(String login, String password) throws RemoteException, InvalidUser;
    Cliente crearCliente(String nombre) throws RemoteException;
    List<Cliente> obtenerClientes() throws RemoteException;
    Cliente actualizarCliente(int id, String nombre) throws RemoteException;
    boolean eliminarCliente(int id) throws RemoteException;

    List<Artista> obtenerArtistas() throws RemoteException;

    List<Evento> obtenerEventos() throws RemoteException;
    Evento obtenerEventoPorID(int id) throws RemoteException;
    List<Evento> obtenerEventosDestacados() throws RemoteException;

    //Ticket comprarTicket(int idPrecio, int idCliente) throws RemoteException;

}

