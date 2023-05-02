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
    @Getter @Setter private int id;
    @Getter @Setter private String titulo;
    @Getter @Setter private String descripcion;
    @Getter @Setter private Date fecha;
    @Getter @Setter private String imagenUrl;
    @Getter @Setter private int aforo;

    public Evento(int id, Attributes attributes) {
        this.id = id;
        this.titulo = attributes.getTitulo();
        this.descripcion = attributes.getDescripcion();
        this.fecha = attributes.getFecha();
        this.imagenUrl = attributes.getImagenUrl();
        this.aforo = attributes.getAforo();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Attributes {
        private String titulo;
        private String descripcion;
        private Date fecha;
        private String imagenUrl;
        private int aforo;
    }
}
