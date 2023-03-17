package rmi.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import rmi.server.entidades.Cliente;
import rmi.server.exceptions.InvalidUser;

public interface IUserService extends Remote {

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
     * Registrar un nuevo usuario
     * @param login
     * @param password
     * @throws RemoteException
     * @throws InvalidUser
     */
    void registrarUsuario(String login, String password) throws RemoteException, InvalidUser;

    /**
     * Crear un nuevo cliente
     * @param nombre
     * @return Cliente creado
     * @throws RemoteException
     */
    Cliente crearCliente(String nombre) throws RemoteException;

    /**
     * Obtener la lista de clientes
     * @return Lista de clientes
     * @throws RemoteException
     */
    List<Cliente> obtenerClientes() throws RemoteException;

    /**
     * Actualizar información del cliente
     * @param id
     * @param nombre
     * @return Cliente actualizado
     * @throws RemoteException
     */
    Cliente actualizarCliente(int id, String nombre) throws RemoteException;

    /**
     * Eliminar un cliente
     * @param id
     * @return true si se eliminó con éxito, false en caso contrario
     * @throws RemoteException
     */
    boolean eliminarCliente(int id) throws RemoteException;
}
