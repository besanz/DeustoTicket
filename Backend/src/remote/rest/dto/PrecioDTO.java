package remote.rest.dto;

import com.google.gson.annotations.SerializedName;
import data.entidades.Precio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrecioDTO {
    private int id;

    @SerializedName("attributes")
    private Precio precio;
}
