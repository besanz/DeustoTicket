package rmi.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.entidades.Staff;
import rmi.server.exceptions.InvalidUser;

public interface IStaffService extends Remote {

    /**
     * Iniciar sesion del staff
     * @param username El nombre de usuario del staff
     * @param password La contraseña del staff
     * @return Staff El staff autenticado, si las credenciales son correctas
     * @throws RemoteException
     * @throws InvalidUser
     */
    Staff loginStaff(String username, String password) throws RemoteException;

    Staff registerStaff(String username, String password) throws RemoteException, InvalidUser;

}
