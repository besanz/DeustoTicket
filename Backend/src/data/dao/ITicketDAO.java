package data.dao;

import data.entidades.Ticket;

import java.util.List;

public interface ITicketDAO {
    void addTicket(Ticket ticket);
    Ticket getById(String id);
    void removeById(String id);
    List<Ticket> getAllTickets();
}
