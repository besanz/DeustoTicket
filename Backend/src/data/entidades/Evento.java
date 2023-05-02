package data.entidades;

import java.util.Date;
import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Evento implements Serializable {

    @PrimaryKey
    @Getter @Setter private String titulo;

    @Getter @Setter private String descripcion;

    @Getter @Setter private Date fecha;

    @Getter @Setter private String createdAt;

    @Getter @Setter private String updatedAt;

    @Getter @Setter private String publishedAt;

    @Getter @Setter private int aforo;
}
