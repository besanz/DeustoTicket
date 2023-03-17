package rmi.server.entidades;

import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable {
    private int id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String portada;
    private int aforo;

    public Evento(int id, String titulo, String descripcion, Date fecha, String portada, int aforo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.portada = portada;
        this.aforo = aforo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    // Getters y setters
    
}
