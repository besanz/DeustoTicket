package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entidades.Cliente;
import entidades.Artista;
import rmi.server.api.IUserService;
import rmi.server.exceptions.InvalidUser;

public class ServerUsuarios extends UnicastRemoteObject implements IUserService {

    private static final long serialVersionUID = 1L;
    private int cont = 0;
    private HashMap<String, String> registeredUsers = null;
    private List<Cliente> clientes = null;
    private List<Artista> artistas = null;
 
    protected ServerUsuarios() throws RemoteException {
        super();
        registeredUsers = new HashMap<String, String>();
        clientes = new ArrayList<>();
        artistas = new ArrayList<>();
    }
    

    @Override
    public String sayHello() throws RemoteException {
        cont++;
        System.out.println(" * Client number: " + cont);
        return "Hello World!";
    }

    @Override
    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        if (registeredUsers.containsKey(login)) {
            if (registeredUsers.get(login).contentEquals(password)) {
                return message;
            } else {
                throw new InvalidUser("Contraseña incorrecta.");
            }
        } else {
            throw new InvalidUser("Usuario no registrado.");
        }
    }

    @Override
    public void registrarUsuario(String login, String password) throws RemoteException, InvalidUser {
        if (!registeredUsers.containsKey(login)) {
            registeredUsers.put(login, password);
        } else {
            throw new InvalidUser("Usuario ya registrado.");
        }
    }

    @Override
    public Cliente crearCliente(String nombre) throws RemoteException {
        Cliente cliente = new Cliente(cont++, nombre);
        clientes.add(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> obtenerClientes() throws RemoteException {
        return clientes;
    }

    @Override
    public Cliente actualizarCliente(int id, String nombre) throws RemoteException {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNombre(nombre);
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarCliente(int id) throws RemoteException {
        return clientes.removeIf(cliente -> cliente.getId() == id);
    }

    @Override
public Artista crearArtista(String nombre, String descripcion, String fechaNacimiento, String foto) throws RemoteException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
        date = formatter.parse(fechaNacimiento);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Artista artista = new Artista(cont++, nombre, descripcion, date, foto);
    artistas.add(artista);
    return artista;
}

@Override
public List<Artista> obtenerArtistas() throws RemoteException {
    return artistas;
}

@Override
public Artista actualizarArtista(int id, String nombre, String descripcion, String fechaNacimiento, String foto) throws RemoteException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
        date = formatter.parse(fechaNacimiento);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    for (Artista artista : artistas) {
        if (artista.getId() == id) {
            artista.setNombre(nombre);
            artista.setDescripcion(descripcion);
            artista.setFechaNacimiento(date);
            artista.setFoto(foto);
            return artista;
        }
    }
    return null;
}

@Override
public boolean eliminarArtista(int id) throws RemoteException {
    return artistas.removeIf(artista -> artista.getId() == id);
}

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
            System.exit(0);
        }

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

        try {
            IUserService objServer = new ServerUsuarios();
            Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
            registry.rebind(name, objServer);
            System.out.println("* Server '" + name + "' active and waiting...");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
