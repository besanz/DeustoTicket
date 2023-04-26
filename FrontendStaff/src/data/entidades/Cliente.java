package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Cliente implements Serializable {
    @PrimaryKey
    @Getter @Setter private int id;
    @Getter @Setter private String nombre;
}

