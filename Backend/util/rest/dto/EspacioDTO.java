package remote.rest.dto;

import com.google.gson.annotations.SerializedName;
import data.entidades.Espacio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspacioDTO {
    private int id;

    @SerializedName("attributes")
    private Espacio espacio;
}
