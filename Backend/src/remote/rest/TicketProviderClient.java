package remote.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import data.entidades.*;
import remote.rest.dto.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class TicketProviderClient {

    private static final String API_BASE_URL = "https://deusto-api.arambarri.eus";
    private static final String TOKEN = "258c263f485581b788b509f2499f49418c640fa412a19ae2a96a7d93a38354f1b06e577ec301a213027acbcde59a9a8ce709862b8e8e6f59c90dbbe6f2a4c43582fa58f384bd7c45016bcd1e61c25358c0e3a9d592dc5e39d60b5825b931ec77ccb228ce133e1360902eb3ec8948aa13ba66bbd8f92df5e1cc5acd00848f1cce";

    public List<Artista> getArtistas() throws IOException {
        String jsonResponse = makeApiRequest("/api/artistas");
        
        // Parse the JSON response to extract the "data" key
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        Type listType = new TypeToken<ArrayList<ArtistaDTO>>() {}.getType();
        List<ArtistaDTO> artistaResponses = new Gson().fromJson(dataArray, listType);

        List<Artista> artistas = new ArrayList<>();
        for (ArtistaDTO artistaResponse : artistaResponses) {
            Artista artista = artistaResponse.getArtista();
            artistas.add(artista);
        }

        return artistas;
    }

    public List<Evento> getEventos() throws IOException {
        String jsonResponse = makeApiRequest("/api/eventos");

        // Parse the JSON response to extract the "data" key
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        Type listType = new TypeToken<ArrayList<EventoDTO>>() {}.getType();
        List<EventoDTO> eventoResponses = new Gson().fromJson(dataArray, listType);

        List<Evento> eventos = new ArrayList<>();
        for (EventoDTO eventoResponse : eventoResponses) {
            Evento evento = eventoResponse.getEvento();
            eventos.add(evento);
        }

        return eventos;
    }

    public List<Espacio> getEspacios() throws IOException {
        String jsonResponse = makeApiRequest("/api/espacios");

        // Parse the JSON response to extract the "data" key
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        Type listType = new TypeToken<ArrayList<EspacioDTO>>() {}.getType();
        List<EspacioDTO> espacioResponses = new Gson().fromJson(dataArray, listType);

        List<Espacio> espacios = new ArrayList<>();
        for (EspacioDTO espacioResponse : espacioResponses) {
            Espacio espacio = espacioResponse.getEspacio();
            espacios.add(espacio);
        }

        return espacios;
    }

     public List<Precio> getPrecios() throws IOException {
        String jsonResponse = makeApiRequest("/api/precios");

        // Parse the JSON response to extract the "data" key
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        Type listType = new TypeToken<ArrayList<PrecioDTO>>() {}.getType();
        List<PrecioDTO> precioResponses = new Gson().fromJson(dataArray, listType);

        List<Precio> precios = new ArrayList<>();
        for (PrecioDTO precioResponse : precioResponses) {
            Precio precio = precioResponse.getPrecio();
            precios.add(precio);
        }

        return precios;
    }

    public Evento getEventoById(int id) throws IOException {
        String jsonResponse = makeApiRequest("/api/eventos/" + id);

        // Parse the JSON response to extract the "data" key
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonObject dataObject = jsonObject.getAsJsonObject("data");

        EventoDTO eventoResponse = new Gson().fromJson(dataObject, EventoDTO.class);
        Evento evento = eventoResponse.getEvento();

        return evento;
    }


    private String makeApiRequest(String endpoint) throws IOException {
        URL url = new URL(API_BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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

    public List<Espacio> getEspaciosByEventoId(int eventoId) throws IOException {
    String jsonResponse = makeApiRequest("/api/eventos/" + eventoId + "/espacios");

    // Parse the JSON response to extract the "data" key
    JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
    JsonArray dataArray = jsonObject.getAsJsonArray("data");

    Type listType = new TypeToken<ArrayList<EspacioDTO>>() {}.getType();
    List<EspacioDTO> espacioResponses = new Gson().fromJson(dataArray, listType);

    List<Espacio> espacios = new ArrayList<>();
    for (EspacioDTO espacioResponse : espacioResponses) {
        Espacio espacio = espacioResponse.getEspacio();
        espacios.add(espacio);
    }

    return espacios;
    }

public Detail getEventDetailById(int id) throws IOException {
    String endpoint = "/api/eventos/" + id + "?populate=*";
    String jsonResponse = makeApiRequest(endpoint);

    // Parse the JSON response to extract the "data" key
    JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
    JsonObject dataObject = jsonObject.getAsJsonObject("data");

    EventoDTO eventoResponse = new Gson().fromJson(dataObject, EventoDTO.class);
    Evento evento = eventoResponse.getEvento();

    // Extract additional attributes from the JSON response
    JsonObject attributesObject = dataObject.getAsJsonObject("attributes");
    String titulo = attributesObject.get("titulo").getAsString();
    String descripcion = attributesObject.get("descripcion").getAsString();
    // Extract other attributes as needed

    // Extract the artistas information
    JsonObject artistasObject = attributesObject.getAsJsonObject("artistas");
    JsonArray artistasDataArray = artistasObject.getAsJsonArray("data");
    List<Artista> artistas = new ArrayList<>();
    for (JsonElement element : artistasDataArray) {
        JsonObject artistaObject = element.getAsJsonObject();
        int artistaId = artistaObject.get("id").getAsInt();
        JsonObject artistaAttributesObject = artistaObject.getAsJsonObject("attributes");
        String nombreArtista = artistaAttributesObject.get("nombre").getAsString();
        // Extract other artistas attributes as needed
        Artista artista = new Artista(artistaId, nombreArtista);
        artistas.add(artista);
    }

    // Extract the espacio information
    JsonObject espacioObject = attributesObject.getAsJsonObject("espacio");
    int espacioId = espacioObject.get("id").getAsInt();
    JsonObject espacioAttributesObject = espacioObject.getAsJsonObject("attributes");
    String nombreEspacio = espacioAttributesObject.get("nombre").getAsString();
    // Extract other espacio attributes as needed
    Espacio espacio = new Espacio(espacioId, nombreEspacio);

    // Extract the precio information
    JsonObject preciosObject = attributesObject.getAsJsonObject("precios");
    JsonArray preciosDataArray = preciosObject.getAsJsonArray("data");
    Precio precio = null;
    for (JsonElement element : preciosDataArray) {
        JsonObject precioObject = element.getAsJsonObject();
        int precioId = precioObject.get("id").getAsInt();
        if (precioId == 1) {
            JsonObject precioAttributesObject = precioObject.getAsJsonObject("attributes");
            String nombrePrecio = precioAttributesObject.get("nombre").getAsString();
            double precioValue = precioAttributesObject.get("precio").getAsDouble();
            int disponibles = precioAttributesObject.get("disponibles").getAsInt();
            int ofertadas = precioAttributesObject.get("ofertadas").getAsInt();
            // Extract other precio attributes as needed
            precio = new Precio(precioId, nombrePrecio, precioValue, disponibles, ofertadas);
            break;
        }
    }

    // Create the Detail object with all the extracted information
    Detail detail = new Detail(evento, titulo, descripcion, artistas, espacio, precio);
    return detail;
}

}