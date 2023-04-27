package service;

import data.entidades.User;
import remote.IRemoteFacade;
import java.rmi.RemoteException;

public class LoginAppService {
    private static LoginAppService instance;
    private IRemoteFacade remoteFacade;

    private LoginAppService(IRemoteFacade remoteFacade) {
        this.remoteFacade = remoteFacade;
    }

    public static LoginAppService getInstance(IRemoteFacade remoteFacade) {
        if (instance == null) {
            instance = new LoginAppService(remoteFacade);
        }
        return instance;
    }

    public User loginUser(String login, String password) throws RemoteException {
        return remoteFacade.loginUser(login, password);
    }
}

