package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import data.entidades.Artista;
import data.entidades.Cliente;
import data.entidades.Evento;
import data.entidades.Ticket;
import rmi.server.api.IUserService;
import rmi.server.exceptions.InvalidUser;

public class ServerUser extends UnicastRemoteObject implements IUserService {
    // Añade aquí tus instancias de DAO
    private static ServerUser instance;

    protected ServerUser() throws RemoteException {
        super();
        // Inicializa tus instancias de DAO aquí
    }

    public static ServerUser getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ServerUser();
        }
        return instance;
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello!";
    }

    @Override
    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        // Aquí puedes validar el usuario utilizando tus instancias de DAO
        return "Hello, " + login + "! Your message is: " + message;
    }

    @Override
    public void registrarUsuario(String login, String password) throws RemoteException, InvalidUser {
        // Implementa aquí la lógica para registrar un usuario utilizando tus instancias de DAO
    }

    @Override
    public boolean validarUsuario(String login, String password) throws RemoteException, InvalidUser {
        // Implementa aquí la lógica para validar un usuario utilizando tus instancias de DAO
        return false;
    }

    @Override
    public void registrarCliente(String login, String password) throws RemoteException, InvalidUser {
        // Implementa aquí la lógica para registrar un cliente utilizando tus instancias de DAO
    }

    @Override
    public Cliente crearCliente(String nombre) throws RemoteException {
        // Implementa aquí la lógica para crear un cliente utilizando tus instancias de DAO
        return null;
    }

    @Override
    public List<Cliente> obtenerClientes() throws RemoteException {
        // Implementa aquí la lógica para obtener clientes utilizando tus instancias de DAO
        return null;
    }

    @Override
    public Cliente actualizarCliente(int id, String nombre) throws RemoteException {
        // Implementa aquí la lógica para actualizar un cliente utilizando tus instancias de DAO
        return null;
    }

    @Override
    public boolean eliminarCliente(int id) throws RemoteException {
        // Implementa aquí la lógica para eliminar un cliente utilizando tus instancias de DAO
        return false;
    }

    @Override
    public List<Artista> obtenerArtistas() throws RemoteException {
        // Implementa aquí la lógica para obtener artistas utilizando tus instancias de DAO
        return null;
    }

    @Override
    public List<Evento> obtenerEventos() throws RemoteException {
        // Implementa aquí la lógica para obtener eventos utilizando tus instancias de DAO
        return null;
    }

    @Override
    public Evento obtenerEventoPorID(int id) throws RemoteException {
        // Implementa aquí la lógica para obtener un evento por ID utilizando tus instancias de DAO
        return null;
    }

    @Override
    public List<Evento> obtenerEventosDestacados() throws RemoteException {
        // Implementa aquí la lógica para obtener eventos destacados utilizando tus instancias de DAO
        return null;
    }
    
    @Override
    public Ticket comprarTicket(int idPrecio, int idCliente) throws RemoteException {
        // Implementa aquí la lógica para comprar un ticket utilizando tus instancias de DAO
        return null;
    }
    
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
            System.exit(0);
        }
    
        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
    
        try {
            IUserService objServer = ServerUser.getInstance();
            Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
            registry.rebind(name, objServer);
            System.out.println("* Server '" + name + "' active and waiting...");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
    