package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.*;

import data.entidades.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @PrimaryKey @Persistent
    @Getter @Setter private String id;

    @Persistent
    @Getter @Setter private String nombreEvento;

    @Persistent
    @Getter @Setter private String fechaEvento;

    @Persistent
    @Getter @Setter private String lugarEvento;

    @Persistent
    @Getter @Setter private double precio;

    @Persistent
    @Getter @Setter private String dni;

    @Persistent
    @Getter @Setter private String titular;

    @Persistent
    @Getter @Setter private int valido;
}
