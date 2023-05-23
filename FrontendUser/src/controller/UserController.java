package controller;

import remote.IFacadeUser;
import data.entidades.*;
import remote.ServiceLocator;
import java.rmi.RemoteException;
import rmi.server.exceptions.InvalidUser;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class UserController {
    private IFacadeUser remoteFacade;
    private User user;
    private final int qrCodeSize = 350;

    public UserController(ServiceLocator serviceLocator) {
        this.remoteFacade = serviceLocator.getRemoteFacade();
    }

    public User loginUser(String login, String password) {
        try {
            this.user = remoteFacade.loginUser(login, password);
            if (user == null) {
                System.out.println("UserController: User not found in the database.");
            } else {
                System.out.println("UserController: User found in the database.");
            }
            return user;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User registerUser(String dni, String name, String surname, String email, String password) {
        try {
            User newUser = remoteFacade.registerUser(dni, name, surname, email, password);
            if (newUser == null) {
                System.out.println("UserController: User registration failed.");
            } else {
                System.out.println("UserController: User registration successful.");
            }
            return newUser;
        } catch (RemoteException | InvalidUser e) {
            e.printStackTrace();
            return null;
        }
    }

    public void logoutUser() {
        this.user = null;
        System.out.println("User has been logged out.");
    }
    
    public IFacadeUser getRemoteFacade() {
        return remoteFacade;
    }

    public void buyTicket(Evento evento, Espacio espacio, Precio precio, User user) {
        try {
            // Aquí debes crear un Ticket con los datos proporcionados
            Ticket ticket = new Ticket();
            ticket.setId(UUID.randomUUID().toString()); // Genera un UUID para el ticket
            ticket.setNombreEvento(evento.getTitulo());

            // Formatear la fecha a String
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = format.format(evento.getFecha());
            ticket.setFechaEvento(formattedDate);

            ticket.setLugarEvento(espacio.getNombre());
            ticket.setPrecio(precio.getValor());
            ticket.setDni(user.getDni());
            ticket.setTitular(user.getNombre() + " " + user.getApellidos());
            ticket.setValido(1); // asumo que un ticket nuevo es siempre válido

            // Guarda el ticket en la base de datos
            remoteFacade.addTicket(ticket);

            // Generar código QR y guardar en un archivo
            String qrCodeFilePath = "ticketQR.png";
            String qrCodeText = String.valueOf(ticket.getId());
            remoteFacade.generateQRCodeImage(qrCodeText, qrCodeFilePath, qrCodeSize);

            // Enviar email con PDF y QR
            String recipientEmail = user.getEmail();
            remoteFacade.sendEmailWithPDFAndQR(recipientEmail, ticket);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

