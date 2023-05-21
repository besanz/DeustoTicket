package remote.rest.transformer;

import data.entidades.Precio;
import remote.rest.dto.PrecioDTO;
import java.util.List;
import java.util.ArrayList;

public class PrecioTransformer {

    private static PrecioTransformer instance;

    private PrecioTransformer() {}

    public static synchronized PrecioTransformer getInstance() {
        if (instance == null) {
            instance = new PrecioTransformer();
        }
        return instance;
    }

    public List<Precio> transform(List<PrecioDTO> precioDTOs) {
        List<Precio> precios = new ArrayList<>();
        for (PrecioDTO precioDTO : precioDTOs) {
            Precio precio = precioDTO.getPrecio();
            precios.add(precio);
        }
        return precios;
    }

    public Precio transform(PrecioDTO precioDTO) {
        return precioDTO.getPrecio();
    }
}