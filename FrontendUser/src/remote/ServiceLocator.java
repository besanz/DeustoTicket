package remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import remote.IFacadeUser;

public class ServiceLocator {
    private IFacadeUser remoteFacade;

    public ServiceLocator(String serverName) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(2000); // asumiendo que el puerto es siempre 2000
            remoteFacade = (IFacadeUser) registry.lookup(serverName);
        } catch (Exception e) {
            throw new RemoteException("Error al localizar el objeto remoto", e);
        }
    }

    public IFacadeUser getRemoteFacade() {
        return remoteFacade;
    }
}

