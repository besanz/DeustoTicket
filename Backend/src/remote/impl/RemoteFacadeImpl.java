package remote.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.entidades.*;
import rmi.server.exceptions.InvalidUser;
import rmi.server.impl.ServerUser;
import rmi.server.impl.ServerStaff;

import remote.IRemoteFacade;

public class RemoteFacadeImpl extends UnicastRemoteObject implements IRemoteFacade {
    private ServerUser serverUser;
    private ServerStaff serverStaff;

    public RemoteFacadeImpl() throws RemoteException {
        serverUser = ServerUser.getInstance();
        serverStaff = ServerStaff.getInstance();
    }

    @Override
    public User loginUser(String login, String password) throws RemoteException {
        return serverUser.loginUser(login, password);
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello!";
    }


    //Llamar a los métodos correspondientes de ServerUser y ServerStaff
}
