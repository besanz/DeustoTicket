package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.IRemoteFacade;

public class Client {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
			System.exit(0);
		}

		String serverIP = "0.0.0.0";
		int serverPort = "1099";
		String serverName = "GuTicketServer";

		IRemoteFacade stubServer = null;

		try {
			Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
			stubServer = (IRemoteFacade) registry.lookup(serverName);
			System.out.println("* Message coming from the server: '" + stubServer.sayHello() + "'");

		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
