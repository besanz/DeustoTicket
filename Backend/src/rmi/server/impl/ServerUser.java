package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import remote.IFacadeUser;
import remote.impl.RemoteUser;

public class ServerUser extends UnicastRemoteObject {
    private static ServerUser instance;

    private ServerUser() throws RemoteException {
        super();
    }

    public static ServerUser getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ServerUser();
        }
        return instance;
    }

    public static void main(String[] args) {
        try {
            String host = "127.0.0.1";
            int port = 2000;
            String serverName = "GuTicketServer";

            System.setProperty("java.security.policy", "../security/java.policy");
            System.setProperty("java.rmi.server.hostname", host);
            IFacadeUser objServer = RemoteUser.getInstance();

            Registry registry = LocateRegistry.createRegistry(port);
            System.out.println("RMI Registry created on port " + port);

            registry.rebind(serverName, objServer);
            System.out.println("* Server '" + "//" + host + ":" + port + "/" + serverName + "' active and waiting...");

            while(true)
            {
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}