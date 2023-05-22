package remote.impl;

import data.dao.*;
import data.dao.impl.*;
import data.entidades.*;
import remote.api.IStaffService;
import remote.api.IUserService;
import rmi.server.exceptions.InvalidUser;
import remote.IRemoteFacade;
import remote.service.*;
import remote.service.qr.QRService;
import remote.service.mail.EmailService;
import remote.service.pdf.PDFService;
import remote.api.paypal.PaypalService;
import remote.rest.gateway.TicketProviderClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import java.util.List;

public class Remote extends UnicastRemoteObject implements IRemoteFacade {
    private static Remote instance;
    private IStaffService staffService;
    private IUserService userService;
    private QRService qrService;
    private PDFService pdfService;
    private EmailService emailService;
    private PaypalService paypalService;
    private TicketProviderClient ticketProviderClient;
    private ITicketDAO ticketDAO;

    private Remote() throws RemoteException {
        staffService = StaffService.getInstance();
        userService = UserService.getInstance();
        qrService = new QRService();
        pdfService = new PDFService(qrService);
        emailService = new EmailService(pdfService);
        ticketProviderClient = TicketProviderClient.getInstance();
        ticketDAO = TicketDAO.getInstance();
    }

    public static synchronized Remote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new Remote();
        }
        return instance;
    }
    
    @Override
    public User loginUser(String login, String password) throws RemoteException {
        User user = userService.loginUser(login, password);
        if (user != null) {
            System.out.println("User " + login + " has logged in.");
        }
        return user;
    }

    @Override
    public User registerUser(String dni, String nombre, String apellidos, String email, String password) throws RemoteException, InvalidUser {
        User newUser = userService.registerUser(dni, nombre, apellidos, email, password);
        System.out.println("User " + email + " has registered.");
        return newUser;
    }

    @Override
    public Staff registerStaff(String username, String password) throws RemoteException, InvalidUser {
        Staff newStaff = staffService.registerStaff(username, password);
        System.out.println("Staff " + username + " has registered.");
        return newStaff;
    }

    @Override
    public Staff loginStaff(String login, String password) throws RemoteException {
        return staffService.loginStaff(login, password);
    }

    @Override
    public List<Artista> obtenerArtistas(int eventoID) throws RemoteException {
        return userService.obtenerArtistas(eventoID);
    }

    @Override
    public Artista obtenerArtistaPorID(int artistaID) throws RemoteException {
        return userService.obtenerArtistaPorID(artistaID);
    }

    @Override
    public List<Evento> obtenerEventos() throws RemoteException {
        return userService.obtenerEventos();
    }

    @Override
    public Espacio obtenerEspacioDeEvento(int eventoID) throws RemoteException {
        return userService.obtenerEspacioDeEvento(eventoID);
    }

    @Override
    public List<User> findAllUsers() throws RemoteException {
        return staffService.findAllUsers();
    }

    @Override
    public void deleteUserByDni(String dni) throws RemoteException {
        staffService.deleteUserByDni(dni);
    }

    @Override
    public void addTicket(Ticket ticket) throws RemoteException {
        ticketDAO.addTicket(ticket);
    }

    @Override
    public void generateQRCodeImage(String text, String filePath, int size) throws RemoteException {
        qrService.generateQRCodeImage(text, filePath, size);
    }

    @Override
    public String readQRCodeImage(String filePath) throws RemoteException {
        return qrService.readQRCodeImage(filePath);
    }

    @Override
    public void sendEmailWithPDFAndQR(String recipientEmail, Ticket ticket) throws RemoteException {
        emailService.sendEmailWithPDFAndQR(recipientEmail, ticket);
    }

    @Override
    public Precio getPrecioByID(int precioId) throws RemoteException {
        return userService.getPrecioByID(precioId);
    }

    @Override
    public void updateTickets(Precio precio) throws RemoteException {
        userService.updateTickets(precio);
    }

    @Override
    public void updateTicketValido(String ticketId) throws RemoteException {
        staffService.updateTicketValido(ticketId);
    }

    @Override
    public void createPayment(String precio) throws RemoteException{
        paypalService.createPayment(precio);
    }

    @Override
    public void removeTicketById(String id) throws RemoteException
    {
        staffService.removeTicketById(id);
    }

    @Override
    public List<Ticket> getAllTickets() throws RemoteException
    {
        return staffService.getAllTickets();
    }

}
