package rmi.client;

import java.rmi.RemoteException;
import remote.ServiceLocator;
import gui.LoginUser;

public class Client {

    public static void main(String[] args) {

        String serverName = "GuTicketServer";

        ServiceLocator serviceLocator;
        try {
            serviceLocator = new ServiceLocator(serverName);
        } catch (Exception e) {
            System.err.println("- Exception running the client: " + e.getMessage());
            e.printStackTrace();cd .
            return;
        }

        java.awt.EventQueue.invokeLater(() -> {
            new LoginUser(serviceLocator).setVisible(true);
        });

    }
}

