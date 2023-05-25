package remote.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.entidades.*;
import rmi.server.exceptions.InvalidUser;

public interface IUserService extends Remote {
    /**
     * Iniciar sesión de un usuario utilizando un nombre de usuario y contraseña.
     * @param login: Nombre de usuario del usuario.
     * @param password: Contraseña del usuario.
     * @return: Objeto de tipo User que representa al usuario que ha iniciado sesión.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    User loginUser(String login, String password) throws RemoteException;

    /**
     * Registrar un nuevo usuario con los datos proporcionados.
     * @param dni: Número de identificación del usuario.
     * @param nombre: Nombre del usuario.
     * @param apellidos: Apellidos del usuario.
     * @param email: Correo electrónico del usuario.
     * @param password: Contraseña del usuario.
     * @return: Objeto de tipo User que representa al nuevo usuario registrado.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     * @throws InvalidUser: Excepción lanzada si el usuario proporcionado es inválido.
     */
    User registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException, InvalidUser;

    /**
     * Obtener una lista de artistas asociados a un evento.
     * @param eventoID: Identificador del evento.
     * @return: Lista de objetos de tipo Artista que representan a los artistas asociados al evento.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    List<Artista> obtenerArtistas(int eventoID) throws RemoteException;

    /**
     * Obtener un artista por su identificador.
     * @param artistaID: Identificador del artista.
     * @return: Objeto de tipo Artista que representa al artista encontrado.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    Artista obtenerArtistaPorID(int artistaID) throws RemoteException;

    /**
     * Obtener una lista de todos los eventos.
     * @return: Lista de objetos de tipo Evento que representan todos los eventos disponibles.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    List<Evento> obtenerEventos() throws RemoteException;

    /**
     * Obtener una lista de eventos destacados.
     * @return: Lista de objetos de tipo Evento que representan los eventos destacados.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    List<Evento> obtenerEventosDestacados() throws RemoteException;

    /**
     * Obtener el espacio asociado a un evento.
     * @param eventoID: Identificador del evento.
     * @return: Objeto de tipo Espacio que representa el espacio asociado al evento.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    Espacio obtenerEspacioDeEvento(int eventoID) throws RemoteException;

    /**
     * Obtener el precio por su identificador.
     * @param precioId: Identificador del precio.
     * @return: Objeto de tipo Precio que representa el precio encontrado.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    Precio getPrecioByID(int precioId) throws RemoteException;

    /**
     * Agregar un ticket a la base de datos.
     * @param ticket: Objeto de tipo Ticket que se desea agregar.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    public void addTicket(Ticket ticket) throws RemoteException;

    /**
     * Actualizar los tickets asociados a un precio.
     * @param precio: Objeto de tipo Precio que contiene los tickets actualizados.
     * @throws RemoteException: Excepción lanzada si ocurre un error en la comunicación remota.
     */
    void updateTickets(Precio precio) throws RemoteException;

    
}