package remote.rest.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import data.entidades.*;
import remote.rest.dto.*;
import remote.rest.transformer.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class JsonUtils {

    ArtistaTransformer artistaTransformer = ArtistaTransformer.getInstance();
    EspacioTransformer espacioTransformer = EspacioTransformer.getInstance();
    EventoTransformer eventoTransformer = EventoTransformer.getInstance();
    PrecioTransformer precioTransformer = PrecioTransformer.getInstance();

    public List<Evento> parseEventos(String jsonResponse) throws IOException {
        // Obtener todos los eventos con precios y clientes
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        if (jsonObject == null) {
            throw new NullPointerException("Error en el parseo del JSON de eventos");
        }
        JsonArray dataArray = jsonObject.getAsJsonArray("data");
        if (dataArray == null) {
            return new ArrayList<>();
        }
        
        // Filtrar eventos que tienen un precio con cliente ID  y construir la lista de eventos filtrados
        List<Evento> eventosFiltrados = new ArrayList<>();
        for (JsonElement element : dataArray) {
            JsonObject eventoObject = element.getAsJsonObject();
            JsonObject attributesObject = eventoObject.getAsJsonObject("attributes");
            if (attributesObject != null) {
                int eventoID = eventoObject.get("id").getAsInt();
                String titulo = attributesObject.get("titulo").getAsString();
                String descripcion = attributesObject.get("descripcion").getAsString();
                String fechaString = attributesObject.get("fecha").getAsString();
                Date fecha = parseFecha(fechaString);
                String createdAt = attributesObject.get("createdAt").getAsString();
                String updatedAt = attributesObject.get("updatedAt").getAsString();
                String publishedAt = attributesObject.get("publishedAt").getAsString();
                int aforo = attributesObject.get("aforo").getAsInt();

                // Obtener precios del evento
                List<Precio> preciosEvento = new ArrayList<>();
                JsonObject preciosObject = attributesObject.getAsJsonObject("precios");
                if (preciosObject != null) {
                    JsonElement preciosDataElement = preciosObject.get("data");
                    if (preciosDataElement != null && preciosDataElement.isJsonArray()) {
                        JsonArray preciosDataArray = preciosDataElement.getAsJsonArray();
                        for (JsonElement precioElement : preciosDataArray) {
                            JsonObject precioObject = precioElement.getAsJsonObject();
                            JsonObject precioAttributesObject = precioObject.getAsJsonObject("attributes");
                            if (precioAttributesObject != null) {
                                int precioID = precioObject.get("id").getAsInt();
                                String nombre = precioAttributesObject.get("nombre").getAsString();
                                double precioValue = precioAttributesObject.get("precio").getAsDouble();
                                int disponibles = precioAttributesObject.get("disponibles").getAsInt();
                                int ofertadas = precioAttributesObject.get("ofertadas").getAsInt();

                                // Obtener el objeto "cliente"
                                Cliente cliente = null;
                                JsonObject clienteObject = precioAttributesObject.getAsJsonObject("cliente");
                                if (clienteObject != null) {
                                    JsonObject clienteDataObject = clienteObject.getAsJsonObject("data");
                                    if (clienteDataObject != null) {
                                        int clienteID = clienteDataObject.get("id").getAsInt();
                                        String nombreCliente = clienteDataObject.getAsJsonObject("attributes")
                                                .get("nombre").getAsString();
                                        String clienteCreatedAt = clienteDataObject.getAsJsonObject("attributes")
                                                .get("createdAt").getAsString();
                                        String clienteUpdatedAt = clienteDataObject.getAsJsonObject("attributes")
                                                .get("updatedAt").getAsString();
                                        String clientePublishedAt = clienteDataObject.getAsJsonObject("attributes")
                                                .get("publishedAt").getAsString();
                                        cliente = new Cliente(clienteID, nombreCliente, clienteCreatedAt,
                                                clienteUpdatedAt, clientePublishedAt);
                                    }
                                }

                                // Filtrar precios con cliente ID 1
                                if (cliente != null && cliente.getId() == 1) {
                                    Precio precioEntity = new Precio(precioID, nombre, precioValue, disponibles, ofertadas, cliente);
                                    preciosEvento.add(precioEntity);
                                }
                            }
                        }
                    }
                }
                // Agregar el evento a la lista de eventos filtrados si tiene precios con cliente ID 1
                if (!preciosEvento.isEmpty()) {
                    Evento evento = new Evento(eventoID, titulo, descripcion, fecha, createdAt, updatedAt, publishedAt, aforo, preciosEvento);
                    eventosFiltrados.add(evento);
                }

            }
        }

        return eventosFiltrados;
    }

    public List<Artista> parseArtistas(String jsonResponse) throws IOException {
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        if (jsonObject == null) {
            throw new NullPointerException("Error en el parseo del JSON");
        }

        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        if (dataObject == null) {
            return new ArrayList<>();
        }

        JsonObject attributesObject = dataObject.getAsJsonObject("attributes");
        if (attributesObject == null) {
            return new ArrayList<>();
        }

        JsonObject artistasObject = attributesObject.getAsJsonObject("artistas");
        if (artistasObject == null) {
            return new ArrayList<>();
        }

        JsonArray artistasArray = artistasObject.getAsJsonArray("data");
        if (artistasArray == null) {
            return new ArrayList<>();
        }

        List<Artista> artistas = new ArrayList<>();
        for (JsonElement artistaElement : artistasArray) {
            JsonObject artistaObject = artistaElement.getAsJsonObject();

            int id = artistaObject.get("id").getAsInt();

            JsonObject artistaAttributes = artistaObject.getAsJsonObject("attributes");

            String nombre = artistaAttributes.get("nombre").getAsString();
            String descripcion = artistaAttributes.get("descripcion").getAsString();
            String fechaNacimiento = artistaAttributes.get("fecha_nacimiento").getAsString();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = formatter.parse(fechaNacimiento);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Artista artista = new Artista();
            artista.setId(id);
            artista.setNombre(nombre);
            artista.setDescripcion(descripcion);
            artista.setFechaNacimiento(date);

            artistas.add(artista);
        }

        return artistas;
    }

    public Artista parseArtista(String jsonResponse) throws IOException {
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

        Artista artista = new Artista();
        artista.setId(dataObject.get("id").getAsInt());
        artista.setNombre(attributesObject.get("nombre").getAsString());
        artista.setDescripcion(attributesObject.get("descripcion").getAsString());

        String fechaNacimiento = attributesObject.get("fecha_nacimiento").getAsString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(fechaNacimiento);
            artista.setFechaNacimiento(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return artista;
    }

    public Espacio parseEspacio(String jsonResponse) throws IOException {
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

        Type type = new TypeToken<EspacioDTO>() {
        }.getType();
        EspacioDTO espacioResponse = new Gson().fromJson(espacioObject, type);

        EspacioTransformer espacioTransformer = EspacioTransformer.getInstance();

        Espacio espacio = espacioTransformer.transform(espacioResponse);

        return espacio;
    }

    private Date parseFecha(String jsonResponse) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // O manejar el error de alguna otra manera
        }
    }

    public String createUpdateTicketsJson(int precioId, int newAvailableTickets) {
        // Convierte el objeto a JSON
        Gson gson = new Gson();
        JsonObject ticketUpdateJson = new JsonObject();
        JsonObject dataJson = new JsonObject();
        JsonObject attributesJson = new JsonObject();
        attributesJson.addProperty("disponibles", newAvailableTickets);
        dataJson.add("attributes", attributesJson);
        dataJson.addProperty("id", precioId);
        ticketUpdateJson.add("data", dataJson);
        return gson.toJson(ticketUpdateJson);
    }

}