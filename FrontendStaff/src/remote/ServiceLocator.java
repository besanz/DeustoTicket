package remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ServiceLocator {
    private IRemoteFacade remoteFacade;

    public ServiceLocator(String remoteUrl) throws RemoteException {
        try {
            remoteFacade = (IRemoteFacade) Naming.lookup(remoteUrl);
        } catch (Exception e) {
            throw new RemoteException("Error al localizar el objeto remoto", e);
        }
    }

    public ServiceLocator(Registry registry) {
        try {
            remoteFacade = (IRemoteFacade) registry.lookup("GuTicketServer");
        } catch (Exception e) {
            System.err.println("Error looking up RemoteFacade in ServiceLocator: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public IRemoteFacade getRemoteFacade() {
        return remoteFacade;
    }
}

