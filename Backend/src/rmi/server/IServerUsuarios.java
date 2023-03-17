package rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerUsuarios extends Remote 
{
	/**
	 * Test message to say hello to client
	 * @param
	 * @return Message
	 * @throws RemoteException
	 */
	String sayHello() throws RemoteException;
	
	/**
	 * Print message to client
	 * @param login
	 * @param password
	 * @param message
	 * @return Message
	 * @throws RemoteException
	 */
	String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser;
	
	/**
	 * Function to register a new user
	 * @param login
	 * @param password
	 * @throws RemoteException
	 */
	void registerUser(String login, String password) throws RemoteException, InvalidUser;

	/**
	 * Método que accederá a la API TicketProvider y nos dará un ArrayList de los distintos eventos que existen.
	 
	 ArrayList <Evento> consultarEventos();

	 /**
	 * Método que accede a la API TicketProvider y nos da un ArrayList de los precios que existen para un evento en concreto.
	 
	 ArrayList <Precio> consultarPrecios(int idEvento);

	 /**
	 * Es el método que generará el ticket final una vez se tengan todos los datos necesarios.
	 
	 Ticket GenerarTicket(Evento evento, Precio precio, Usuario usuario);

	 /**
	 * Método que enviará el ticket por email al usuario que lo ha comprado.
	 
	 void EnviarTicket (Ticket ticket, String email);
	*/
}