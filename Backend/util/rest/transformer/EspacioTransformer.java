package remote.rest.transformer;

import data.entidades.Espacio;
import remote.rest.dto.EspacioDTO;
import java.util.List;
import java.util.ArrayList;

public class EspacioTransformer {

    private static EspacioTransformer instance;

    private EspacioTransformer() {}

    public static synchronized EspacioTransformer getInstance() {
        if (instance == null) {
            instance = new EspacioTransformer();
        }
        return instance;
    }

    public List<Espacio> transform(List<EspacioDTO> espacioDTOs) {
        List<Espacio> espacios = new ArrayList<>();
        for (EspacioDTO espacioDTO : espacioDTOs) {
            Espacio espacio = espacioDTO.getEspacio();
            espacios.add(espacio);
        }
        return espacios;
    }

    public Espacio transform(EspacioDTO espacioDTO) {
        return espacioDTO.getEspacio();
    }
}
