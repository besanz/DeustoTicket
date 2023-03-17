package rmi.server.entidades;

import java.io.Serializable;

public class StaffMember implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String cargo;

    public StaffMember(int id, String nombre, String cargo) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    // Getters y setters
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método toString
    @Override
    public String toString() {
        return "StaffMember{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
