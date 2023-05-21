package data.dao.impl;

import data.DBConfig;
import data.dao.ITicketDAO;
import data.entidades.Ticket;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.List;

public class TicketDAO implements ITicketDAO {
    private static TicketDAO instance;

    private TicketDAO() {
    }

    public static TicketDAO getInstance() {
        if (instance == null) {
            instance = new TicketDAO();
        }
        return instance;
    }

    public void updateTicketValido(String ticketId) {
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Ticket ticket = pm.getObjectById(Ticket.class, ticketId);
            if (ticket.getValido() == 1) {
                ticket.setValido(0);
                System.out.println("El QR del ticket " + ticketId + " ha sido validado exitosamente");
            }
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }


    public void addTicket(Ticket ticket) {
        PersistenceManager pm = DBConfig.getPersistenceManager();
        try {
            pm.makePersistent(ticket);
        } finally {
            pm.close();
        }
    }

    public Ticket getById(String id) {
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Ticket ticket = null;
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            ticket = pm.getObjectById(Ticket.class, id);

            if (ticket != null) {
                System.out.println("TicketDAO: Ticket found by id.");
            } else {
                System.out.println("TicketDAO: Ticket not found by id.");
            }

            tx.commit();
        } catch (Exception e) {
            System.err.println("TicketDAO: Exception occurred while finding ticket by id.");
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return ticket;
    }

    public void removeById(String id) {
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            Ticket ticket = pm.getObjectById(Ticket.class, id);

            if (ticket != null) {
                pm.deletePersistent(ticket);
                System.out.println("TicketDAO: Ticket deleted successfully.");
            } else {
                System.out.println("TicketDAO: Failed to delete ticket. No ticket found with the given id.");
            }

            tx.commit();
        } catch (Exception e) {
            System.err.println("TicketDAO: Exception occurred while deleting ticket.");
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public List<Ticket> getAllTickets() {
        PersistenceManager pm = DBConfig.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        List<Ticket> tickets = null;

        try {
            tx.begin();
            Query<Ticket> query = pm.newQuery(Ticket.class);
            tickets = (List<Ticket>) query.execute();

            if (tickets != null) {
                System.out.println("TicketDAO: Successfully fetched all tickets.");
            } else {
                System.out.println("TicketDAO: Failed to fetch tickets.");
            }

            tx.commit();
        } catch (Exception e) {
            System.err.println("TicketDAO: Exception occurred while fetching all tickets.");
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }

        return tickets;
    }
}
