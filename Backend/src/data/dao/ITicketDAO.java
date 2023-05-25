package data.dao;

import data.entidades.Ticket;

import java.util.List;

public interface ITicketDAO {
    /**
     * Agregar un ticket a la base de datos.
     * @param ticket: Objeto de tipo Ticket que se desea agregar.
     */
    void addTicket(Ticket ticket);

    /**
     * Actualizar el estado de validez de un ticket dado.
     * @param ticketId: Identificador del ticket a actualizar.
     */
    void updateTicketValido(String ticketId);

    /**
     * Obtener un ticket por su identificador.
     * @param id: Identificador del ticket.
     * @return: Objeto de tipo Ticket que representa al ticket encontrado.
     */
    Ticket getById(String id);

    /**
     * Eliminar un ticket por su identificador.
     * @param id: Identificador del ticket a eliminar.
     */
    void removeById(String id);

    /**
     * Obtener una lista de todos los tickets.
     * @return: Lista de objetos de tipo Ticket que representan todos los tickets disponibles.
     */
    List<Ticket> getAllTickets();

}
