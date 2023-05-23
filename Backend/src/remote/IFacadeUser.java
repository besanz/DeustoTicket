package remote;

import data.entidades.*;

import rmi.server.exceptions.InvalidUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFacadeUser extends Remote {

    User loginUser(String login, String password) throws RemoteException;

    User registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException, InvalidUser;

    // Metodos para obtener datos de la API pasandole un eventoID
    List<Artista> obtenerArtistas(int eventoID) throws RemoteException;

    Artista obtenerArtistaPorID(int artistaID) throws RemoteException;

    List<Evento> obtenerEventos() throws RemoteException;

    Espacio obtenerEspacioDeEvento(int eventoID) throws RemoteException;

    void addTicket(Ticket ticket) throws RemoteException;

    void generateQRCodeImage(String text, String filePath, int size) throws RemoteException;

    void sendEmailWithPDFAndQR(String recipientEmail, Ticket ticket) throws RemoteException;

    Precio getPrecioByID(int precioId) throws RemoteException;
    
    void updateTickets(Precio precio) throws RemoteException;

    void createPayment(String precio) throws RemoteException;
}
