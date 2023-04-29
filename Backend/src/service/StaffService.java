package service;

import java.rmi.RemoteException;

import data.dao.IStaffDAO;
import data.dao.impl.StaffDAO;
import data.entidades.Staff;
import rmi.server.api.IStaffService;
import rmi.server.exceptions.InvalidUser;

public class StaffService implements IStaffService {
    private StaffDAO staffDAO;
    private static StaffService instance;

    public StaffService() {
        staffDAO = StaffDAO.getInstance();
    }

    public static StaffService getInstance() {
        if (instance == null) {
            instance = new StaffService();
        }
        return instance;
    }

    public String sayHello() throws RemoteException {
        return "Hello!";
    }

    public String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser {
        return "Hello, admin" + login + "! Your message is: " + message;
    }

    public Staff loginStaff(String login, String password) throws RemoteException {
        return staffDAO.findByUsernameAndPassword(login, password);
    }
}