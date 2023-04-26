package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import data.entidades.Cliente;
import data.entidades.Staff;
import rmi.server.api.IStaffService;
import rmi.server.exceptions.InvalidUser;

public class ServerStaff extends UnicastRemoteObject implements IStaffService {
    private static ServerStaff instance;

    protected ServerStaff() throws RemoteException {
        super();
    }

    public static ServerStaff getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ServerStaff();
        }
        return instance;
    }

    @Override
    public Staff loginStaff(String username, String password) throws RemoteException, InvalidUser {
        return null;
    }

    @Override
    public Cliente registrarCliente(String nombre) throws RemoteException {
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

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
            System.exit(0);
        }

        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

        try {
            IStaffService objServer = new ServerStaff();
            Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
            registry.rebind(name, objServer);
            System.out.println("* Server '" + name + "' active and waiting...");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
