package remote;

import data.entidades.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteFacade extends Remote {
    User loginUser(String login, String password) throws RemoteException;
    String sayHello() throws RemoteException;
}
