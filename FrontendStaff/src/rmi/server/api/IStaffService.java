package rmi.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.entidades.*;
import rmi.server.exceptions.InvalidUser;

public interface IStaffService extends Remote {
    String sayHello() throws RemoteException;
    String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser;
    void registrarUsuario(String login, String password) throws RemoteException, InvalidUser;
    Staff loginStaff(String login, String password) throws RemoteException;

    List<Artista> obtenerArtistas() throws RemoteException;

    List<Evento> obtenerEventos() throws RemoteException;

    List<Precio> obtenerPrecios() throws RemoteException;

    List<Espacio> obtenerEspacios() throws RemoteException;

}

