package remote.rest.gateway;

import remote.rest.dto.*;
import remote.rest.transformer.*;
import remote.rest.gateway.token.TokenProvider;
import remote.rest.utils.HttpUtils;
import remote.rest.utils.JsonUtils;
import java.io.IOException;
import java.util.List;

public class TicketGateway {
    
    private final TokenProvider tokenProvider;
    private final JsonUtils utils;
    
    private static TicketGateway instance = null;

    private TicketGateway() {
        this.tokenProvider = TokenProvider.getInstance();
        this.utils = new JsonUtils();
    }

    // Método estático de acceso global
    public static synchronized TicketGateway getInstance() {
        if (instance == null) {
            instance = new TicketGateway();
        }
        return instance;
    }

    public List<EventoDTO> getEventos() throws IOException {
        String token = tokenProvider.getToken();
        String jsonResponse = HttpUtils.performGetRequest("/api/eventos?populate=precios.cliente", token);
        return utils.parseEventos(jsonResponse);
    }

    public List<ArtistaDTO> getArtistasByEventoID(int eventoID) throws IOException {
        String token = tokenProvider.getToken();
        String jsonResponse = HttpUtils.performGetRequest("/api/eventos/" + eventoID + "?populate=artistas", token);
        return utils.parseArtistas(jsonResponse);
    }

    public ArtistaDTO getArtistaByID(int artistaID) throws IOException {
        String token = tokenProvider.getToken();
        String jsonResponse = HttpUtils.performGetRequest("/api/artistas/" + artistaID, token);
        return utils.parseArtista(jsonResponse);
    }

    public EspacioDTO getEspacioByEventoID(int eventoID) throws IOException {
        String token = tokenProvider.getToken();
        String jsonResponse = HttpUtils.performGetRequest("/api/eventos/" + eventoID + "?populate=espacio", token);
        return utils.parseEspacio(jsonResponse);
    }

    public void updateTickets(Precio precio) throws IOException {
        String token = tokenProvider.getToken();
        String jsonInputString = utils.createUpdateTicketsJson(precioId, newAvailableTickets);
        HttpUtils.performPutRequest("/api/precios/" + precioId, jsonInputString, token);
    }
}
