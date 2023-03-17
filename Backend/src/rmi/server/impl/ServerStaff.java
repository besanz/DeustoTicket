package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import rmi.server.api.IStaffService;
import rmi.server.entidades.StaffMember;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStaff extends UnicastRemoteObject implements IStaffService {

    private static final long serialVersionUID = 1L;
    private List<StaffMember> staffMembers;
    private AtomicInteger idCounter;

    protected ServerStaff() throws RemoteException {
        super();
        staffMembers = new ArrayList<>();
        idCounter = new AtomicInteger(0);
    }

    @Override
    public StaffMember crearStaffMember(String nombre, String cargo) throws RemoteException {
        int id = idCounter.incrementAndGet();
        StaffMember staffMember = new StaffMember(id, nombre, cargo);
        staffMembers.add(staffMember);
        return staffMember;
    }

    @Override
    public List<StaffMember> obtenerStaffMembers() throws RemoteException {
        return staffMembers;
    }

    @Override
    public StaffMember actualizarStaffMember(int id, String nombre, String cargo) throws RemoteException {
        for (StaffMember staffMember : staffMembers) {
            if (staffMember.getId() == id) {
                staffMember.setNombre(nombre);
                staffMember.setCargo(cargo);
                return staffMember;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarStaffMember(int id) throws RemoteException {
        return staffMembers.removeIf(staffMember -> staffMember.getId() == id);
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
