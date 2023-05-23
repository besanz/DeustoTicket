package rmi.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.ServiceLocator;
import remote.IFacadeUser;
import gui.LoginUser;

public class Client {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
            System.exit(0);
        }

        String serverIP = "127.0.0.1";
        int serverPort = 2000;
        String serverName = "GuTicketServer";

        String remoteUrl = String.format("//%s:%d/%s", serverIP, serverPort, serverName);
        System.out.println("Remote URL: " + remoteUrl);

        IFacadeUser stubServer = null;
        final ServiceLocator serviceLocator;
        try {
            Registry registry = LocateRegistry.getRegistry(serverPort);
            stubServer = (IFacadeUser) registry.lookup(serverName);
            serviceLocator = new ServiceLocator(registry);

        } catch (Exception e) {
            System.err.println("- Exception running the client: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUser(serviceLocator).setVisible(true);
            }
        });

    }
}
