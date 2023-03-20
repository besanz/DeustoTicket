package rmi.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import rmi.server.exceptions.InvalidUser;

import entidades.Staff;

public interface IStaffService extends Remote {

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
	void registrarUsuario(String login, String password) throws RemoteException, InvalidUser;

    /**
     * Función para crear un nuevo miembro del personal
     * @param nombre
     * @param cargo
     * @return StaffMember
     * @throws RemoteException
     */
    Staff crearStaffMember(String nombre, String cargo) throws RemoteException;

    /**
     * Función para obtener la lista de todos los miembros del personal
     * @return List<StaffMember>
     * @throws RemoteException
     */
    List<Staff> obtenerStaffMembers() throws RemoteException;

    /**
     * Función para actualizar la información de un miembro del personal
     * @param id
     * @param nombre
     * @param cargo
     * @return StaffMember
     * @throws RemoteException
     */
    Staff actualizarStaffMember(int id, String nombre, String cargo) throws RemoteException;

    /**
     * Función para eliminar a un miembro del personal
     * @param id
     * @return boolean
     * @throws RemoteException
     */
    boolean eliminarStaffMember(int id) throws RemoteException;
}