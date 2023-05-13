package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
@Queries({
    @Query(
        name="findByLoginAndPassword",
        value="SELECT FROM data.entidades.User WHERE email == e && password == p PARAMETERS String e, String p"
    ),
    @Query(
        name="findByEmail",
        value="SELECT FROM data.entidades.User WHERE email == e PARAMETERS String e"
    ),
     @Query(
        name="findAllUsers",
        value="SELECT FROM data.entidades.User"
    )
})
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

