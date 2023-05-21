package remote.rest.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    private static final String API_BASE_URL = "https://deusto-api.arambarri.eus";

    public static String performGetRequest(String endpoint, String token) throws IOException {
        URL apiUrl = new URL(API_BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        if (connection == null) {
            throw new NullPointerException("No se pudo abrir la conexión");
        }
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error en la llamada a la API: codigo de respuesta " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        connection.disconnect();
        return sb.toString();
    }

    public static void performPutRequest(String endpoint, String jsonInputString, String token) throws IOException {
        URL apiUrl = new URL(API_BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setDoOutput(true);

        // Escribe el objeto JSON a la conexión
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"))) {
            writer.write(jsonInputString);
            writer.flush();
        }

        // Verifica la respuesta
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error en la llamada a la API: codigo de respuesta " + responseCode);
        }

        connection.disconnect();
    }
}
