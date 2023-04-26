package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import data.entidades.Cliente;
import data.entidades.Evento;
import data.entidades.Precio;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Ticket implements Serializable {
    @PrimaryKey
    @Getter @Setter private int id;
    @Getter @Setter private Evento evento;
    @Getter @Setter private Precio precio;
    @Getter @Setter private Cliente cliente;
}
