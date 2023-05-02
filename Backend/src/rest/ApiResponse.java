package rest;

import java.util.ArrayList;
import java.util.List;
import data.entidades.*;

public class ApiResponse {
    private List<Evento> eventos = new ArrayList<>(); // Initialize with an empty list

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}