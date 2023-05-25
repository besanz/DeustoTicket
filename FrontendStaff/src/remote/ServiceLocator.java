package remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import remote.IFacadeStaff;

public class ServiceLocator {
    private IFacadeStaff remoteFacade;

    public ServiceLocator(String serverName) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(1999); // asumiendo que el puerto es siempre 2000
            remoteFacade = (IFacadeStaff) registry.lookup(serverName);
        } catch (Exception e) {
            throw new RemoteException("Error al localizar el objeto remoto", e);
        }
    }

    public IFacadeStaff getRemoteFacade() {
        return remoteFacade;
    }
}
