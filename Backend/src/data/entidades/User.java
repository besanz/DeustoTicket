package data.entidades;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class User {
	@PrimaryKey
    @Getter @Setter private String dni;
    @Getter @Setter private String nombre;
    @Getter @Setter private String apellidos;
    @Getter @Setter private String email;
    @Getter @Setter private String password;
    @Getter @Setter private Ticket ticket;
}