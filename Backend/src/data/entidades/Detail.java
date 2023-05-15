package data.entidades;

import lombok.*;
import lombok.Setter;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Detail {
    private Evento evento;
    private String titulo;
    private String descripcion;
    private List<Artista> artistas;
    private Espacio espacio;
    private Precio precio;
}
