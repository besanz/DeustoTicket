package remote.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.entidades.Staff;
import data.entidades.User;
import data.entidades.Ticket;

import rmi.server.exceptions.InvalidUser;

public interface IStaffService extends Remote {

    /**
     * Iniciar sesión del personal.
     * @param username: El nombre de usuario del personal.
     * @param password: La contraseña del personal.
     * @return: Objeto de tipo Staff que representa al personal autenticado, si las credenciales son correctas.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    Staff loginStaff(String username, String password) throws RemoteException;

    /**
     * Registrar un nuevo personal con un nombre de usuario y contraseña.
     * @param username: El nombre de usuario del nuevo personal.
     * @param password: La contraseña del nuevo personal.
     * @return: Objeto de tipo Staff que representa al nuevo personal registrado.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     * @throws InvalidUser: Excepción lanzada si el usuario proporcionado es inválido.
     */
    Staff registerStaff(String username, String password) throws RemoteException, InvalidUser;

    /**
     * Recuperar una lista de todos los usuarios registrados.
     * @return: Lista de objetos de tipo User que representan a todos los usuarios registrados.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    List<User> findAllUsers() throws RemoteException;

    /**
     * Eliminar un usuario por su número de identificación (DNI).
     * @param dni: Número de identificación del usuario a eliminar.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    void deleteUserByDni(String dni) throws RemoteException;

    /**
     * Actualizar el estado de validez de un ticket dado.
     * @param ticketId: Identificador del ticket a actualizar.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    void updateTicketValido(String ticketId) throws RemoteException;

    /**
     * Eliminar un ticket por su identificador.
     * @param id: Identificador del ticket a eliminar.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    void removeTicketById(String id) throws RemoteException;

    /**
     * Recuperar una lista de todos los tickets.
     * @return: Lista de objetos de tipo Ticket que representan todos los tickets disponibles.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    List<Ticket> getAllTickets() throws RemoteException;

}
