package data.dao;

import data.entidades.Ticket;

import java.util.List;

public interface ITicketDAO {
    void addTicket(Ticket ticket);
    void updateTicketValido(String ticketId);
    Ticket getById(String id);
    void removeById(String id);
    List<Ticket> getAllTickets();
}
