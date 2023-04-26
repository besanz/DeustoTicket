package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.entidades.Artista;
import data.entidades.Cliente;
import data.entidades.Evento;
import data.entidades.Ticket;

import rmi.server.api.IUserService;
import rmi.server.exceptions.InvalidUser;

public class ServerUser extends UnicastRemoteObject implements IUserService {

    protected ServerUser() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return null;
    }

    @Override
    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        return null;
    }

    @Override
    public void registrarUsuario(String login, String password) throws RemoteException, InvalidUser {

    }

    @Override
    public boolean validarUsuario(String login, String password) throws RemoteException, InvalidUser {
        return false;
    }

    @Override
    public void registrarCliente(String login, String password) throws RemoteException, InvalidUser {

    }

    @Override
    public Cliente crearCliente(String nombre) throws RemoteException {
        return null;
    }

    @Override
    public List<Cliente> obtenerClientes() throws RemoteException {
        return null;
    }

    @Override
    public Cliente actualizarCliente(int id, String nombre) throws RemoteException {
        return null;
    }

    @Override
    public boolean eliminarCliente(int id) throws RemoteException {
        return false;
    }

    @Override
    public List<Artista> obtenerArtistas() throws RemoteException {
        return null;
    }

    @Override
    public List<Evento> obtenerEventos() throws RemoteException {
        return null;
    }

    @Override
    public Evento obtenerEventoPorID(int id) throws RemoteException {
        return null;
    }

    @Override
    public List<Evento> obtenerEventosDestacados() throws RemoteException {
        return null;
    }

    @Override
    public Ticket comprarTicket(int idPrecio, int idCliente) throws RemoteException {
        return null;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
            System.exit(0);
        }

        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

        try {
            IUserService objServer = new ServerUser();
            Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
            registry.rebind(name, objServer);
            System.out.println("* Server '" + name + "' active and waiting...");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
