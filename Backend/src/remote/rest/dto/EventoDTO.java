package remote.rest.dto;

import com.google.gson.annotations.SerializedName;
import data.entidades.Evento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoDTO {
    private int id;

    @SerializedName("attributes")
    private Evento evento;
}
