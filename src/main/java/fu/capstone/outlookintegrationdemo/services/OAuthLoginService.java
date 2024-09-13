package fu.capstone.outlookintegrationdemo.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class OAuthLoginService {
    private static final String TOKEN_URL = "https://login.microsoftonline.com/{tenant_id}/oauth2/v2.0/token";
    private static final String CLIENT_ID = "insert-your-client-id-here";
    private static final String CLIENT_SECRET = "insert-your-client-secret-here";
    private static final String SCOPE = "https://graph.microsoft.com/.default";
    private static final String GRANT_TYPE = "client_credentials";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        Map<Object, Object> data = Map.of(
                "client_id", CLIENT_ID,
                "client_secret", CLIENT_SECRET,
                "scope", SCOPE,
                "grant_type", GRANT_TYPE
        );

        String form = data.entrySet()
                .stream()
                .map(entry -> URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOKEN_URL.replace("{tenant_id}", "insert-your-tenant-id-here")))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
