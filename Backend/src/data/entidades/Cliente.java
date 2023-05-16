package data.entidades;

import java.io.Serializable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    @Getter @Setter private int id;

    @Getter @Setter private String nombre;

    @Getter @Setter private String createdAt;

    @Getter @Setter private String updatedAt;

    @Getter @Setter private String publishedAt;
}
