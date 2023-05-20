package data.entidades;

import java.util.Date;
import java.io.Serializable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class Artista implements Serializable {

    @Getter @Setter private int id;

    @Getter @Setter private String nombre;

    @Getter @Setter private String descripcion;

    @Getter @Setter private Date fechaNacimiento;

}
