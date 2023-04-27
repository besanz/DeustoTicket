package controller;

import service.LoginAppService;
import data.entidades.User;
import remote.ServiceLocator;
import java.rmi.RemoteException;

public class LoginController {
    private ServiceLocator serviceLocator;
    private LoginAppService loginAppService;

    public LoginController(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
        this.loginAppService = LoginAppService.getInstance(serviceLocator.getRemoteFacade());
    }

    public User loginUser(String login, String password) {
        try {
            return loginAppService.loginUser(login, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
