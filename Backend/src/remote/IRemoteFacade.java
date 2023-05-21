package remote;

import data.entidades.*;

import rmi.server.exceptions.InvalidUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteFacade extends Remote {

    User loginUser(String login, String password) throws RemoteException;

    User registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException, InvalidUser;

    Staff registerStaff(String username, String password) throws RemoteException, InvalidUser;

    Staff loginStaff(String login, String password) throws RemoteException;

    // Metodos para obtener datos de la API pasandole un eventoID
    List<Artista> obtenerArtistas(int eventoID) throws RemoteException;

    Artista obtenerArtistaPorID(int artistaID) throws RemoteException;

    List<Evento> obtenerEventos() throws RemoteException;

    Espacio obtenerEspacioDeEvento(int eventoID) throws RemoteException;

    // Metodos que van al DAO
    List<User> findAllUsers() throws RemoteException;

    void deleteUserByDni(String dni) throws RemoteException;

    void addTicket(Ticket ticket) throws RemoteException;

    void generateQRCodeImage(String text, String filePath, int size) throws RemoteException;

    String readQRCodeImage(String filePath) throws RemoteException;

    void sendEmailWithPDFAndQR(String recipientEmail, String subject, String body, Ticket ticket) throws RemoteException;

    Precio getPrecioByID(int precioId) throws RemoteException;
    
    void updateTickets(Precio precio) throws RemoteException;

    void updateTicketValido(String ticketId) throws RemoteException;

    void createPayment(String precio) throws RemoteException;
}
