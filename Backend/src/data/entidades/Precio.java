package data.entidades;

import java.io.Serializable;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Precio implements Serializable {

    @Getter @Setter private int id;
    @Getter @Setter private String nombre;
    @Getter @Setter private double precio;
    @Getter @Setter private int disponibles;
    @Getter @Setter private int ofertadas;
    @Getter @Setter private Date createdAt;
    @Getter @Setter private Date updatedAt;
    @Getter @Setter private Date publishedAt;
    @Getter @Setter private Cliente cliente;

    public double getValor() {
        return this.precio;
    }

    public Precio(int id, String nombre, double precio, int disponibles, int ofertadas, Cliente cliente) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponibles = disponibles;
        this.ofertadas = ofertadas;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Precio [id=" + id + ", nombre=" + nombre + ", precio=" + precio
                + ", disponibles=" + disponibles + ", ofertadas=" + ofertadas
                + ", cliente=" + cliente + ", createdAT=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }

}
