package controller;

import service.StaffService;
import data.entidades.Staff;
import remote.ServiceLocator;
import java.rmi.RemoteException;

public class StaffController {
    private StaffService staffService;

    public StaffController(ServiceLocator serviceLocator) {
        this.staffService = StaffService.getInstance();
    }

    public Staff loginStaff(String login, String password) {
        try {
            return staffService.loginStaff(login, password); // Directly call loginStaff() method on staffService object
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
