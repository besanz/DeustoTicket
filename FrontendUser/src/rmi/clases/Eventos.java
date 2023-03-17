package rmi.clases;
import java.util.Date;


//@PersistenceCapable
public class Eventos{
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String portada;
    private int aforo;
    /*Para las relaciones:
    @Column(name="ESPACIO_ID")
    Espacios espacio;
    @Column(name="ARTISTAS_ID")
    Artistas artista;
    */

        
    public Eventos(String titulo, String descripcion, Date fecha, String portada, int aforo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.portada = portada;
        this.aforo = aforo;
    }


    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getPortada() {
        return portada;
    }
    public void setPortada(String portada) {
        this.portada = portada;
    }
    public int getAforo() {
        return aforo;
    }
    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
}
