package remote.service;

import java.rmi.RemoteException;

import data.dao.IStaffDAO;
import data.dao.impl.StaffDAO;
import data.dao.IUserDAO;
import data.dao.impl.UserDAO;

import data.entidades.Staff;
import data.entidades.User;
import rmi.remote.api.IStaffService;
import rmi.server.exceptions.InvalidUser;

import java.util.List;

public class StaffService implements IStaffService {
    private StaffDAO staffDAO;
    private UserDAO userDAO;

    private static StaffService instance;

    public StaffService() {
        staffDAO = StaffDAO.getInstance();
        userDAO = UserDAO.getInstance();
    }

    public static StaffService getInstance() {
        if (instance == null) {
            instance = new StaffService();
        }
        return instance;
    }

    public Staff loginStaff(String login, String password) throws RemoteException {
        return staffDAO.findByUsernameAndPassword(login, password);
    }

    public Staff registerStaff(String username, String password) throws InvalidUser {
        if (staffDAO.findByUsername(username) != null) {
            throw new InvalidUser("Username is already registered.");
        }
        Staff newStaff = new Staff(username, password);
        staffDAO.addStaff(newStaff);
        return newStaff;
    }

    @Override
    public List<User> findAllUsers() throws RemoteException {
        return userDAO.findAllUsers();
    }

    @Override
    public void deleteUserByDni(String dni) throws RemoteException {
        userDAO.deleteUserByDni(dni);
    }
}