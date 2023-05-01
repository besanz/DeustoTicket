package data.entidades;

import java.io.Serializable;
import javax.jdo.annotations.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
@Queries({
    @Query(
        name="findByUsernameAndPassword",
        value="SELECT FROM data.entidades.Staff WHERE username == u && password == p PARAMETERS String u, String p"
    ),
    @Query(
        name="findByUsername",
        value="SELECT FROM data.entidades.Staff WHERE username == u PARAMETERS String u"
    )
})
public class Staff implements Serializable {

    @PrimaryKey @Persistent
    @Getter @Setter private int id;

    @Persistent
    @Getter @Setter private String username;

    @Persistent
    @Getter @Setter private String password;

    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }
}


