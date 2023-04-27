package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import data.dao.UserDAO;
import data.dao.impl.UserDAOImpl;
import data.entidades.*;
import remote.IRemoteFacade;
import remote.impl.RemoteFacadeImpl;
import rmi.server.exceptions.InvalidUser;
import rmi.server.api.IUserService;



public class ServerUser extends UnicastRemoteObject implements IRemoteFacade, IUserService {
    private static ServerUser instance;
    private UserDAO userDAO;

    private ServerUser() throws RemoteException {
        userDAO = UserDAOImpl.getInstance();
    }

    public static ServerUser getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ServerUser();
        }
        return instance;
    }

    @Override
    public User loginUser(String login, String password) throws RemoteException {
        return userDAO.findByLoginAndPassword(login, password);
    }


    @Override
    public String sayHello() throws RemoteException {
        return "Hello!";
    }

    @Override
    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        return "Hello, " + login + "! Your message is: " + message;
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
    // Verifica que se proporcionen los argumentos correctos: [host] [port] [server]
    if (args.length != 3) {
        System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
        System.exit(0);
    }

    // Construye el nombre del objeto remoto utilizando los argumentos proporcionados
    String name = "//" + args[0] + ":" + args[1] + "/" + "GuTicketServer";

    try {
        // Obtiene la instancia de IRemoteFacade
        IRemoteFacade objServer = ServerUser.getInstance();
        
        // Crea y configura el registro RMI con el número de puerto proporcionado
        Registry registry = LocateRegistry.createRegistry(Integer.valueOf(args[1]));
        
        // Registra el objeto remoto en el registro RMI
        registry.rebind(name, objServer);
        
        // Muestra un mensaje indicando que el servidor está activo y esperando conexiones
        System.out.println("* Server '" + name + "' active and waiting...");
    } catch (Exception e) {
        System.err.println("- Exception running the server: " + e.getMessage());
        e.printStackTrace();
    }
}


} 