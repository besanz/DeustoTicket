package remote.rest.dto;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import data.entidades.Precio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrecioDTO  implements Serializable {
    private int id;

    @SerializedName("attributes")
    private Precio precio;

}
