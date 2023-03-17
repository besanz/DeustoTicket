package rmi.clases;

import java.util.Date;

public class Artistas{
    String nombre;
    String descripcion;
    Date fecha_nacimiento;
    String foto;

    public Artistas(String nombre, String descripcion, Date fecha_nacimiento, String foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.foto = foto;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
        
}