package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
@Query(
    name="findByLoginAndPassword",
    value="SELECT FROM data.entidades.User WHERE email == e && password == p PARAMETERS String e, String p"
)
public class User implements Serializable{
    @PrimaryKey @Persistent
    @Getter @Setter private String dni;

    @Persistent
    @Getter @Setter private String nombre;

    @Persistent
    @Getter @Setter private String apellidos;

    @Persistent
    @Unique
    @Getter @Setter private String email;

    @Persistent
    @Getter @Setter private String password;
}

