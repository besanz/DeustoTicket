package remote;

import java.rmi.Naming;
import java.rmi.RemoteException;


public class ServiceLocator {
    private IRemoteFacade remoteFacade;

    public ServiceLocator(String remoteUrl) throws RemoteException {
        try {
            remoteFacade = (IRemoteFacade) Naming.lookup(remoteUrl);
        } catch (Exception e) {
            throw new RemoteException("Error al localizar el objeto remoto", e);
        }
    }

    public IRemoteFacade getRemoteFacade() {
        return remoteFacade;
    }
}

