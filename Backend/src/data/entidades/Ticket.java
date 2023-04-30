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
    @Getter @Setter private int id;

    @Persistent
    @Getter @Setter private Evento evento;

    @Persistent
    @Getter @Setter private Precio precio;

    @Persistent
    @Getter @Setter private Cliente cliente;
}
