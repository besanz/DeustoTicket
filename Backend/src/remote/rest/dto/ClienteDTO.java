package remote.rest.dto;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import data.entidades.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO implements Serializable {
    private int id;

    @SerializedName("attributes")
    private Cliente cliente;
}
