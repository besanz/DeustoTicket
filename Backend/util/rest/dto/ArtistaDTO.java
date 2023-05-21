package remote.rest.dto;

import com.google.gson.annotations.SerializedName;
import data.entidades.Artista;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistaDTO {
    private int id;

    @SerializedName("attributes")
    private Artista artista;
}
