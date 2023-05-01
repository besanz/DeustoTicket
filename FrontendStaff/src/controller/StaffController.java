package controller;

import service.StaffService;
import data.entidades.Staff;
import remote.ServiceLocator;
import java.rmi.RemoteException;
import rmi.server.exceptions.InvalidUser;

public class StaffController {
    private StaffService staffService;

    public StaffController(ServiceLocator serviceLocator) {
        this.staffService = StaffService.getInstance();
    }

    public Staff loginStaff(String login, String password) {
        try {
            Staff staff = staffService.loginStaff(login, password);
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
            Staff newStaff = staffService.registerStaff(username, password);
            if (newStaff == null) {
                System.out.println("StaffController: Staff registration failed.");
            } else {
                System.out.println("StaffController: Staff registration successful.");
            }
            return newStaff;
        } catch (InvalidUser e) {
            e.printStackTrace();
            return null;
        }
    }

}
