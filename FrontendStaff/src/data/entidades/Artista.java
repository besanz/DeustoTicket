package data.entidades;

import java.util.Date;
import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Artista implements Serializable {
    @PrimaryKey
    @Getter @Setter private int id;
    @Getter @Setter private String nombre;
    @Getter @Setter private String descripcion;
    @Getter @Setter private Date fechaNacimiento;
    @Getter @Setter private String foto;

}
