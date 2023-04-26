package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import data.entidades.Cliente;
import data.entidades.Evento;
import data.entidades.Precio;

@PersistenceCapable
public class Ticket implements Serializable {
    @PrimaryKey
    private int id;
    private Evento evento;
    private Precio precio;
    private Cliente cliente;

    public Ticket(int id, Evento evento, Precio precio, Cliente cliente) {
        this.id = id;
        this.evento = evento;
        this.precio = precio;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Precio getPrecio() {
        return precio;
    }

    public void setPrecio(Precio precio) {
        this.precio = precio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
