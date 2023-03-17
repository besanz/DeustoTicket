package rmi.clases;


public class Precios{
    String nombre;
    double precio;
    int disponibles;
    int ofertadas;

    public Precios(String nombre, double precio, int disponibles, int ofertadas) {
        this.nombre = nombre;
        this.precio = precio;
        this.disponibles = disponibles;
        this.ofertadas = ofertadas;
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

    
}