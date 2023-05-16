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

    public double getValor() {
        return this.precio;
    }
}
