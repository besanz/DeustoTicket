package entidades;

import java.util.Date;

public class Artista {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaNacimiento;
    private String foto;

    public Artista(int id, String nombre, String descripcion, Date fechaNacimiento, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
