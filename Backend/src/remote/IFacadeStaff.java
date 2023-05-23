package remote;

import data.entidades.*;

import rmi.server.exceptions.InvalidUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFacadeStaff extends Remote {

    Staff registerStaff(String username, String password) throws RemoteException, InvalidUser;

    Staff loginStaff(String login, String password) throws RemoteException;

    List<User> findAllUsers() throws RemoteException;

    void deleteUserByDni(String dni) throws RemoteException;

    String readQRCodeImage(String filePath) throws RemoteException;

    void updateTicketValido(String ticketId) throws RemoteException;

    void removeTicketById(String id) throws RemoteException;

    List<Ticket> getAllTickets() throws RemoteException;
}
