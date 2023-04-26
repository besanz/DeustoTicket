package data.dao;

import data.entidades.Evento;
import java.util.List;

public interface EventoDAO {
    Evento findById(int id);
    List<Evento> findAll();
    List<Evento> findDestacados();
}
