package remote.rest.transformer;

import data.entidades.Artista;
import remote.rest.dto.ArtistaDTO;
import java.util.List;
import java.util.ArrayList;

public class ArtistaTransformer {

    private static ArtistaTransformer instance;

    private ArtistaTransformer() {}

    public static synchronized ArtistaTransformer getInstance() {
        if (instance == null) {
            instance = new ArtistaTransformer();
        }
        return instance;
    }

    public List<Artista> transform(List<ArtistaDTO> artistaDTOs) {
        List<Artista> artistas = new ArrayList<>();
        for (ArtistaDTO artistaDTO : artistaDTOs) {
            Artista artista = artistaDTO.getArtista();
            artistas.add(artista);
        }
        return artistas;
    }

    public Artista transform(ArtistaDTO artistaDTO) {
        return artistaDTO.getArtista();
    }
}