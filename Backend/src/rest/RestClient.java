import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.io.FileReader;


public class RestClient {
    public static void main(String[] args) throws IOException {
        //Set up the URL of the REST API endpoint
        URL url = new URL("https://deusto-api.arambarri.eus/api/artistas");

        //Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //Set the HTTP request method
        connection.setRequestMethod("GET");

        //Add headers to the request if necessary
        String token = "258c263f485581b788b509f2499f49418c640fa412a19ae2a96a7d93a38354f1b06e577ec301a213027acbcde59a9a8ce709862b8e8e6f59c90dbbe6f2a4c43582fa58f384bd7c45016bcd1e61c25358c0e3a9d592dc5e39d60b5825b931ec77ccb228ce133e1360902eb3ec8948aa13ba66bbd8f92df5e1cc5acd00848f1cce";
        connection.setRequestProperty("Authorization", "Bearer " + token);

        //Get the response content
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            System.out.println(sb.toString());
        }

        //Close the connection
        connection.disconnect();
    }
}
