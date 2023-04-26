package rmi.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.entidades.Cliente;
import data.entidades.Staff;
import rmi.server.exceptions.InvalidUser;

public interface IStaffService extends Remote {

    /**
     * Iniciar sesión del staff
     * @param username El nombre de usuario del staff
     * @param password La contraseña del staff
     * @return Staff El staff autenticado, si las credenciales son correctas
     * @throws RemoteException
     * @throws InvalidUser
     */
    Staff loginStaff(String username, String password) throws RemoteException, InvalidUser;

    /**
     * Registrar un nuevo cliente
     * @param nombre El nombre del cliente
     * @return Cliente El cliente registrado
     * @throws RemoteException
     */
    Cliente registrarCliente(String nombre) throws RemoteException;

    /**
     * Obtener la lista de todos los clientes
     * @return List<Cliente> La lista de todos los clientes
     * @throws RemoteException
     */
    List<Cliente> obtenerClientes() throws RemoteException;

    /**
     * Actualizar la información de un cliente
     * @param id El ID del cliente a actualizar
     * @param nombre El nuevo nombre del cliente
     * @return Cliente El cliente actualizado
     * @throws RemoteException
     */
    Cliente actualizarCliente(int id, String nombre) throws RemoteException;

    /**
     * Eliminar un cliente
     * @param id El ID del cliente a eliminar
     * @return boolean Indicando si la eliminación fue exitosa o no
     * @throws RemoteException
     */
    boolean eliminarCliente(int id) throws RemoteException;

    // Agregar métodos para validar el QR de los tickets aquí.
}
