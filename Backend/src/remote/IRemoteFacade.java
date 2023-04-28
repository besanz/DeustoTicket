package remote;

import data.entidades.Staff;
import data.entidades.User;
import rmi.server.exceptions.InvalidUser;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteFacade extends Remote {
    User loginUser(String login, String password) throws RemoteException;
    Staff loginStaff(String login, String password) throws RemoteException;
    String sayHello() throws RemoteException;
    String sayMessage(String login, String password, String message) throws RemoteException, InvalidUser;
}
