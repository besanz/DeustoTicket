package remote;

import data.entidades.*;

import rmi.server.exceptions.InvalidUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFacadeStaff extends Remote {

    /**
    * Registra un nuevo miembro del personal con un nombre de usuario y contraseña dados.
    * @param username: Nombre de usuario del nuevo miembro del personal.
    * @param password: Contraseña del nuevo miembro del personal.
    * @return: Objeto de tipo Staff que representa al nuevo miembro del personal registrado.
    * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
    * @throws InvalidUser: Excepción lanzada si el usuario proporcionado es inválido.
    */
    Staff registerStaff(String username, String password) throws RemoteException, InvalidUser;

    /** 
    * Inicia sesión de un miembro del personal utilizando un nombre de usuario y contraseña.
    * @param login: Nombre de usuario del miembro del personal.
    * @param password: Contraseña del miembro del personal.
    * @return: Objeto de tipo Staff que representa al miembro del personal que ha iniciado sesión.
    * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
    */
    Staff loginStaff(String login, String password) throws RemoteException;

    /** 
    * Recupera una lista de todos los usuarios registrados.
    * @return: Lista de objetos de tipo User que representan a todos los usuarios registrados.
    * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
    */
    List<User> findAllUsers() throws RemoteException;

    /** 
    * Elimina un usuario por su número de identificación (DNI).
    * @param dni: Número de identificación del usuario a eliminar.
    * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
    */
    void deleteUserByDni(String dni) throws RemoteException;

    /** 
    * Lee un código QR de una imagen dada.
    * @param filePath: Ruta del archivo de imagen que contiene el código QR.
    * @return: Cadena de texto que representa el contenido del código QR leído.
    * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
    */
    String readQRCodeImage(String filePath) throws RemoteException;

    /** 
    * Actualiza el estado de validez de un ticket dado.
    * @param ticketId: Identificador del ticket a actualizar.
    * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
    */
    void updateTicketValido(String ticketId) throws RemoteException;

    /** 
    * Elimina un ticket por su identificador.
    * @param id: Identificador del ticket a eliminar.
    * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
    */
    void removeTicketById(String id) throws RemoteException;

    /** 
    * Recupera una lista de todos los tickets.
    * @return: Lista de objetos de tipo Ticket que representan todos los tickets disponibles.
    * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
    */
    List<Ticket> getAllTickets() throws RemoteException;

}
