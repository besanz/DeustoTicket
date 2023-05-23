package remote.impl;

import data.dao.*;
import data.dao.impl.*;
import data.entidades.*;
import remote.api.IStaffService;
import remote.api.IUserService;
import rmi.server.exceptions.InvalidUser;
import remote.IFacadeStaff;
import remote.service.StaffService;
import remote.service.qr.QRService;
import remote.service.mail.EmailService;
import remote.service.pdf.PDFService;
import remote.api.paypal.PaypalService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import java.util.List;

public class RemoteStaff extends UnicastRemoteObject implements IFacadeStaff {
    private static RemoteStaff instance;
    private IStaffService staffService;
    private QRService qrService;

    private RemoteStaff() throws RemoteException {
        staffService = StaffService.getInstance();
        qrService = new QRService();
    }

    public static synchronized RemoteStaff getInstance() throws RemoteException {
        if (instance == null) {
            instance = new RemoteStaff();
        }
        return instance;
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
    public List<User> findAllUsers() throws RemoteException {
        return staffService.findAllUsers();
    }

    @Override
    public void deleteUserByDni(String dni) throws RemoteException {
        staffService.deleteUserByDni(dni);
    }

    @Override
    public String readQRCodeImage(String filePath) throws RemoteException {
        return qrService.readQRCodeImage(filePath);
    }

    @Override
    public void updateTicketValido(String ticketId) throws RemoteException {
        staffService.updateTicketValido(ticketId);
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
