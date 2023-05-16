package remote.rest.dto;

import com.google.gson.annotations.SerializedName;
import data.entidades.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private int id;

    @SerializedName("attributes")
    private Cliente cliente;
}
