package rmi.server.entidades;

import java.io.Serializable;

public class Precio implements Serializable {
    private int id;
    private String nombre;
    private double precio;
    private int disponibles;
    private int ofertadas;

    public Precio(int id, String nombre, double precio, int disponibles, int ofertadas) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponibles = disponibles;
        this.ofertadas = ofertadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public int getOfertadas() {
        return ofertadas;
    }

    public void setOfertadas(int ofertadas) {
        this.ofertadas = ofertadas;
    }

    // Getters y setters
    
}
