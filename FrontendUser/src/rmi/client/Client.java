package client;

import java.rmi.RemoteException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import remote.ServiceLocator;
import gui.LoginUser;

public class Client {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
            System.exit(0);
        }

        try {
            // Establecer el aspecto visual de la aplicación para que se vea como el sistema operativo en el que se ejecuta
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        String serverIP = args[0];
        int serverPort = Integer.parseInt(args[1]);
        String serverName = args[2];
        String remoteUrl = String.format("rmi://%s:%d/%s", serverIP, serverPort, serverName);

        try {
            ServiceLocator serviceLocator = new ServiceLocator(remoteUrl);
            LoginUser loginUser = new LoginUser(serviceLocator);
            loginUser.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
