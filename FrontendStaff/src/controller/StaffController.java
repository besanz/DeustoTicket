package controller;

import data.entidades.Staff;
import remote.ServiceLocator;
import java.rmi.RemoteException;
import rmi.server.exceptions.InvalidUser;
import remote.IRemoteFacade;

public class StaffController {
    
    private IRemoteFacade remoteFacade;

    public StaffController(ServiceLocator serviceLocator) {
        this.remoteFacade = serviceLocator.getRemoteFacade();
    }

    public Staff loginStaff(String login, String password) {
        try {
            Staff staff = remoteFacade.loginStaff(login, password);
            if (staff == null) {
                System.out.println("StaffController: Staff not found in the database.");
            } else {
                System.out.println("StaffController: Staff found in the database.");
            }
            return staff;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Staff registerStaff(String username, String password) {
        try {
            Staff newStaff = remoteFacade.registerStaff(username, password);
            if (newStaff == null) {
                System.out.println("StaffController: Staff registration failed.");
            } else {
                System.out.println("StaffController: Staff registration successful.");
            }
            return newStaff;
        } catch (RemoteException | InvalidUser e) {
            e.printStackTrace();
            return null;
        }
    }
        
    public IRemoteFacade getRemoteFacade() {
        return remoteFacade;
    }
}
