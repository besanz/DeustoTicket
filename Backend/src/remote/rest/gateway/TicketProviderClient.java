package remote.rest.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import data.entidades.*;
import remote.rest.dto.*;
import remote.rest.transformer.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class TicketProviderClient {

    private static final String API_BASE_URL = "https://deusto-api.arambarri.eus";
    private static final String TOKEN = "258c263f485581b788b509f2499f49418c640fa412a19ae2a96a7d93a38354f1b06e577ec301a213027acbcde59a9a8ce709862b8e8e6f59c90dbbe6f2a4c43582fa58f384bd7c45016bcd1e61c25358c0e3a9d592dc5e39d60b5825b931ec77ccb228ce133e1360902eb3ec8948aa13ba66bbd8f92df5e1cc5acd00848f1cce";

    private static TicketProviderClient instance = null;

    private TicketProviderClient() {
        // Constructor privado para prevenir su instanciacion.
    }

    // Método estático de acceso global
    public static synchronized TicketProviderClient getInstance() {
        if (instance == null) {
            instance = new TicketProviderClient();
        }
        return instance;
    }

    ArtistaTransformer artistaTransformer = ArtistaTransformer.getInstance();
    EspacioTransformer espacioTransformer = EspacioTransformer.getInstance();
    EventoTransformer eventoTransformer = EventoTransformer.getInstance();
    PrecioTransformer precioTransformer = PrecioTransformer.getInstance();

    //Obtener la lista de eventos total para el main. 
    public List<Evento> getEventos() throws IOException {
        String jsonResponse = makeApiRequest("/api/eventos");
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        if (jsonObject == null) {
            throw new NullPointerException("Error en el parseo del JSON");
        }
        JsonArray dataArray = jsonObject.getAsJsonArray("data");
        if (dataArray == null) {
            return new ArrayList<>(); 
        }
        Type listType = new TypeToken<ArrayList<EventoDTO>>() {}.getType();
        List<EventoDTO> eventoResponses = new Gson().fromJson(dataArray, listType);

        List<Evento> eventos = eventoTransformer.transform(eventoResponses);

        return eventos;
    }

    //Obtiene la información de evento por su ID.
    public Evento getEventoByID(int eventoID) throws IOException {
        String jsonResponse = makeApiRequest("/api/eventos/" + eventoID);
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        if (jsonObject == null) {
            throw new NullPointerException("Error en el parseo del JSON");
        }
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        if (dataObject == null) {
            return null; 
        }

        EventoDTO eventoResponse = new Gson().fromJson(dataObject, EventoDTO.class);
        Evento evento = eventoTransformer.transform(eventoResponse);

        return evento;
    }

    public List<Artista> getArtistasByEventoID(int eventoID) throws IOException {
        String jsonResponse = makeApiRequest("/api/eventos/" + eventoID + "?populate=artistas");
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        if (jsonObject == null) {
            throw new NullPointerException("Error en el parseo del JSON");
        }
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        if (dataObject == null) {
            return new ArrayList<>(); 
        }
        JsonObject artistasObject = dataObject.getAsJsonObject("attributes").getAsJsonObject("artistas");
        if (artistasObject == null) {
            return new ArrayList<>(); 
        }
        JsonArray dataArray = artistasObject.getAsJsonArray("data");
        if (dataArray == null) {
            return new ArrayList<>(); 
        }

        Type listType = new TypeToken<ArrayList<ArtistaDTO>>() {}.getType();
        List<ArtistaDTO> artistaResponses = new Gson().fromJson(dataArray, listType);

        List<Artista> artistas = artistaTransformer.transform(artistaResponses);

        return artistas;
    }

    public Artista getArtistaByID(int artistaID) throws IOException {
        String jsonResponse = makeApiRequest("/api/artistas/" + artistaID);
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        if (jsonObject == null) {
            throw new NullPointerException("Error en el parseo del JSON");
        }
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        if (dataObject == null) {
            return null; 
        }

        ArtistaDTO artistaResponse = new Gson().fromJson(dataObject, ArtistaDTO.class);
        Artista artista = artistaTransformer.transform(artistaResponse);

        return artista;
    }

    public Precio getPrecioByEventoID(int eventoID) throws IOException {
        String jsonResponse = makeApiRequest("/api/eventos/" + eventoID + "?populate=precios");
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        if (jsonObject == null) {
            throw new NullPointerException("Error en el parseo del JSON");
        }
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        if (dataObject == null) {
            return null;
        }
        JsonObject preciosObject = dataObject.getAsJsonObject("attributes").getAsJsonObject("precios");
        if (preciosObject == null) {
            return null;
        }
        JsonArray preciosArray = preciosObject.getAsJsonArray("data");
        if (preciosArray == null) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<PrecioDTO>>() {}.getType();
        List<PrecioDTO> precioResponses = new Gson().fromJson(preciosArray, listType);

        Precio precio = null;
        for (PrecioDTO precioResponse : precioResponses) {
            if (precioResponse.getId() == 1) {
                precio = precioResponse.getPrecio();
                break;
            }
        }

        return precio;
    }

    public Espacio getEspacioByEventoID(int eventoID) throws IOException {
        String jsonResponse = makeApiRequest("/api/eventos/" + eventoID + "?populate=espacio");
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        if (jsonObject == null) {
            throw new NullPointerException("Error en el parseo del JSON");
        }
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        if (dataObject == null) {
            return null; 
        }
        JsonObject attributesObject = dataObject.getAsJsonObject("attributes");
        if (attributesObject == null) {
            return null; 
        }
        JsonObject espacioObject = attributesObject.getAsJsonObject("espacio").getAsJsonObject("data");
        if (espacioObject == null) {
            return null; 
        }

        Type type = new TypeToken<EspacioDTO>() {}.getType();
        EspacioDTO espacioResponse = new Gson().fromJson(espacioObject, type);

        EspacioTransformer espacioTransformer = EspacioTransformer.getInstance();

        Espacio espacio = espacioTransformer.transform(espacioResponse);

        return espacio;
    }

    private String makeApiRequest(String endpoint) throws IOException {
        URL url = new URL(API_BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection == null) {
            throw new NullPointerException("No se pudo abrir la conexión");
        }
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error en la llamada a la API: codigo de respuesta " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        connection.disconnect();
        return sb.toString();
    }

}