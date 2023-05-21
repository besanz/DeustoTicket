package remote.rest.transformer;

import data.entidades.Evento;
import remote.rest.dto.EventoDTO;
import java.util.List;
import java.util.ArrayList;

public class EventoTransformer {

    private static EventoTransformer instance;

    private EventoTransformer() {}

    public static synchronized EventoTransformer getInstance() {
        if (instance == null) {
            instance = new EventoTransformer();
        }
        return instance;
    }

    public List<Evento> transform(List<EventoDTO> eventoDTOs) {
        List<Evento> eventos = new ArrayList<>();
        for (EventoDTO eventoDTO : eventoDTOs) {
            Evento evento = eventoDTO.getEvento();
            evento.setId(eventoDTO.getId());  // Set the id from EventoDTO to Evento
            eventos.add(evento);
        }
        return eventos;
    }

    public Evento transform(EventoDTO eventoDTO) {
        Evento evento = eventoDTO.getEvento();
        evento.setId(eventoDTO.getId());  // Set the id from EventoDTO to Evento
        return evento;
    }
}
