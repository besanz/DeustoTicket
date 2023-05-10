package data.entidades;

import java.io.Serializable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class Espacio implements Serializable {

    @Getter @Setter private int id;

    @Getter @Setter private String nombre;

    @Getter @Setter private String direccion;
}