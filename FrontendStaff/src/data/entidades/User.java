package data.entidades;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class User {
	@Getter @Setter private String dni;
	@Getter @Setter private String nombre;
	@Getter @Setter private String apellidos;
	@Getter @Setter private String email;
	@Persistent(mappedBy = "usuario")
	@Getter @Setter private Ticket ticket;
}