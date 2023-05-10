package data.entidades;

import java.io.Serializable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    @Getter @Setter private int id;

    @Getter @Setter private String nombre;
}
