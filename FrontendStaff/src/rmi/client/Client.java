package rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.server.IServerStaff;


public class Client {

	public static void main(String[] args) {
		if (args.length != 3) 
		{
			System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) 
		{
			System.setSecurityManager(new SecurityManager());
		}

		IServerStaff stubServer = null;
		/**
		 * Try test message
		 */
		try 
		{
			Registry registry = LocateRegistry.getRegistry(((Integer.valueOf(args[1]))));
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			//stubServer = (IServer) java.rmi.Naming.lookup(name);
			stubServer = (IServerStaff) registry.lookup(name);
			System.out.println("* Message coming from the server: '" + stubServer.sayHello() + "'");
			
		} 
		catch (Exception e) 
		{
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
		
				
		/**
		 * Try registering user
		 */
		try
		{			
			stubServer.registerUser("Test1", "Test1");
			System.out.println("* Added user Test1");
			
			stubServer.registerUser("Test2", "Test2");
			System.out.println("* Added user Test2");
			
			stubServer.registerUser("Test3", "Test3");
			System.out.println("* Added user Test3");
			
			stubServer.registerUser("Test3", "Test3");
			System.out.println("* Added user Test3");
		}
		
		catch (Exception e)
		{
			System.err.println("- Exception running the client: " + e.getMessage());
		}
		
		
		
		/**
		 * Try say message
		 */
		try
		{
			System.out.println("El jokin es un tajas");
		}
		
		catch (Exception e)
		{
			System.err.println("- Exception running the client: " + e.getMessage());
		}
		
	}
}