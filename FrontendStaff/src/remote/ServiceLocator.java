package remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ServiceLocator {
    private IFacadeStaff remoteFacade;

    public ServiceLocator(String remoteUrl) throws RemoteException {
        try {
            remoteFacade = (IFacadeStaff) Naming.lookup(remoteUrl);
        } catch (Exception e) {
            throw new RemoteException("Error al localizar el objeto remoto", e);
        }
    }

    public ServiceLocator(Registry registry) {
        try {
            remoteFacade = (IFacadeStaff) registry.lookup("GuTicketServer");
        } catch (Exception e) {
            System.err.println("Error looking up RemoteFacade in ServiceLocator: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public IFacadeStaff getRemoteFacade() {
        return remoteFacade;
    }
}
