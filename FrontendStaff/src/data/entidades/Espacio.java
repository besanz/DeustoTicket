package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Espacio implements Serializable {

    @PrimaryKey @Persistent
    @Getter @Setter private int id;

    @Persistent
    @Getter @Setter private String nombre;

    @Persistent
    @Getter @Setter private String direccion;

    
}