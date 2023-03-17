package rmi.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entidades.Ticket;

public interface ITicketService extends Remote {
    
    /**
     * Comprar un ticket para un cliente
     * @param idPrecio
     * @param idCliente
     * @return Ticket comprado
     * @throws RemoteException
     */
    Ticket comprarTicket(int idPrecio, int idCliente) throws RemoteException;

    /**
     * Cancelar un ticket
     * @param id
     * @return true si se canceló con éxito, false en caso contrario
     * @throws RemoteException
     */
    boolean cancelarTicket(int id) throws RemoteException;
}
