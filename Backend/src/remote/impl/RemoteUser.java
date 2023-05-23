package remote.impl;

import data.dao.*;
import data.dao.impl.*;
import data.entidades.*;
import remote.api.IStaffService;
import remote.api.IUserService;
import rmi.server.exceptions.InvalidUser;
import remote.IFacadeUser;
import remote.service.UserService;
import remote.service.qr.QRService;
import remote.service.mail.EmailService;
import remote.service.pdf.PDFService;
import remote.api.paypal.PaypalService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import java.util.List;

public class RemoteUser extends UnicastRemoteObject implements IFacadeUser {
    private static RemoteUser instance;
    private IUserService userService;
    private QRService qrService;
    private PDFService pdfService;
    private EmailService emailService;
    private PaypalService paypalService;

    private RemoteUser() throws RemoteException {
        userService = UserService.getInstance();
        qrService = new QRService();
        pdfService = new PDFService(qrService);
        emailService = new EmailService(pdfService);
    }

    public static synchronized RemoteUser getInstance() throws RemoteException {
        if (instance == null) {
            instance = new RemoteUser();
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
    public void addTicket(Ticket ticket) throws RemoteException {
        userService.addTicket(ticket);
    }

    @Override
    public void generateQRCodeImage(String text, String filePath, int size) throws RemoteException {
        qrService.generateQRCodeImage(text, filePath, size);
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
    public void createPayment(String precio) throws RemoteException{
        paypalService.createPayment(precio);
    }
}
