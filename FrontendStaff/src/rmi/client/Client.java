package rmi.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.ServiceLocator;
import remote.IRemoteFacade;
import gui.LoginStaff;

public class Client {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
            System.exit(0);
        }

        String serverIP = "127.0.0.1";
        int serverPort = 1999;
        String serverName = "GuTicketServer";

        String remoteUrl = String.format("//%s:%d/%s", serverIP, serverPort, serverName);
        System.out.println("Remote URL: " + remoteUrl);

        IRemoteFacade stubServer = null;
        final ServiceLocator serviceLocator;
        try {
            Registry registry = LocateRegistry.getRegistry(serverPort);
            stubServer = (IRemoteFacade) registry.lookup(serverName);

            System.out.println("* Message coming from the admin server: '" + stubServer.sayHello() + "'");
            serviceLocator = new ServiceLocator(registry);

        } catch (Exception e) {
            System.err.println("- Exception running the client: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginStaff(serviceLocator).setVisible(true);
            }
        });
    }
}