package data.entidades;


import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Staff implements Serializable {
    @PrimaryKey
    @Getter @Setter private int id;
    @Getter @Setterprivate String nombre;

}
