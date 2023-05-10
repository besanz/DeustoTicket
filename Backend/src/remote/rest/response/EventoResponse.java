package remote.rest.response;

import com.google.gson.annotations.SerializedName;
import data.entidades.Evento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoResponse {
    private int id;

    @SerializedName("attributes")
    private Evento evento;
}
