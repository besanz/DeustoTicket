package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Precio implements Serializable {

    @PrimaryKey @Persistent
    @Getter @Setter private int id;

    @Persistent
    @Getter @Setter private String nombre;

    @Persistent
    @Getter @Setter private double precio;

    @Persistent
    @Getter @Setter private int disponibles;

    @Persistent
    @Getter @Setter private int ofertadas;
}