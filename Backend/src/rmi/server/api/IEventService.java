package rmi.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import rmi.server.entidades.Evento;
import rmi.server.entidades.Precio;

public interface IEventService extends Remote {
    
    /**
     * Crear un nuevo evento
     * @param titulo
     * @param descripcion
     * @param fecha
     * @param portada
     * @param aforo
     * @return Evento creado
     * @throws RemoteException
     */
    Evento crearEvento(String titulo, String descripcion, String fecha, String portada, int aforo) throws RemoteException;

    /**
     * Obtener la lista de eventos
     * @return Lista de eventos
     * @throws RemoteException
     */
    List<Evento> obtenerEventos() throws RemoteException;

    /**
     * Actualizar un evento existente
     * @param id
     * @param titulo
     * @param descripcion
     * @param fecha
     * @param portada
     * @param aforo
     * @return Evento actualizado
     * @throws RemoteException
     */
    Evento actualizarEvento(int id, String titulo, String descripcion, String fecha, String portada, int aforo) throws RemoteException;

    /**
     * Eliminar un evento
     * @param id
     * @return true si se eliminó con éxito, false en caso contrario
     * @throws RemoteException
     */
    boolean eliminarEvento(int id) throws RemoteException;

    /**
     * Crear un nuevo precio
     * @param nombre
     * @param precio
     * @param disponibles
     * @param ofertadas
     * @return Precio creado
     * @throws RemoteException
     */
    Precio crearPrecio(String nombre, double precio, int disponibles, int ofertadas) throws RemoteException;

    /**
     * Obtener la lista de precios
     * @return Lista de precios
     * @throws RemoteException
     */
    List<Precio> obtenerPrecios() throws RemoteException;

    /**
     * Actualizar un precio existente
     * @param id
     * @param nombre
     * @param precio
     * @param disponibles
     * @param ofertadas
     * @return Precio actualizado
     * @throws RemoteException
     */
    Precio actualizarPrecio(int id, String nombre, double precio, int disponibles, int ofertadas) throws RemoteException;

    /**
     * Eliminar un precio
     * @param id
     * @return true si se eliminó con éxito, false en caso contrario
     * @throws RemoteException
     */
    boolean eliminarPrecio(int id) throws RemoteException;
}
