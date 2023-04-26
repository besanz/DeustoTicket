package data.dao.impl;

import data.dao.EventoDAO;
import data.entidades.Evento;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class EventoDAOImpl implements EventoDAO {
    private List<Evento> eventos;

    public EventoDAOImpl() {
        eventos = new ArrayList<>();
    }

    @Override
    public Evento findById(int id) {
        for (Evento evento : eventos) {
            if (evento.getId() == id) {
                return evento;
            }
        }
        return null;
    }

    @Override
    public List<Evento> findAll() {
        return eventos;
    }

    @Override
    public List<Evento> findDestacados() {
        List<Evento> eventosDestacados = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate nextWeek = now.plusWeeks(1);

        for (Evento evento : eventos) {
            LocalDate eventoDate = evento.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (eventoDate.isAfter(now) && eventoDate.isBefore(nextWeek)) {
                eventosDestacados.add(evento);
            }
        }

        return eventosDestacados;
    }
}
