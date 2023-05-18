package data.entidades;

import java.io.Serializable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

public class Precio implements Serializable {

    @Getter @Setter private int id;
    @Getter @Setter private String nombre;
    @Getter @Setter private double precio;
    @Getter @Setter private int disponibles;
    @Getter @Setter private int ofertadas;
    @Getter @Setter private Cliente cliente;

    public double getValor() {
        return this.precio;
    }

    @Override
    public String toString() {
        return "Precio [id=" + id + ", nombre=" + nombre + ", precio=" + precio
                + ", disponibles=" + disponibles + ", ofertadas=" + ofertadas
                + ", cliente=" + cliente + "]";
    }

}
