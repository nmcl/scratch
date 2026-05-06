import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class OllamaJavaExample {

    public static void main(String[] args) throws Exception {
        String ollamaUrl = "http://localhost:11434/api/generate";
        String model = "gemma3";  // change to your model name
        String prompt = "Hello, how are you today?";

        // Build JSON body
        String jsonBody = String.format("""
            {
              "model": "%s",
              "prompt": "%s"
            }
            """, model, escapeJson(prompt));

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ollamaUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());

        int code = resp.statusCode();
        String body = resp.body();

        System.out.println("HTTP Status: " + code);
        System.out.println("Response body:");
        System.out.println(body);
    }

    // A minimal JSON string escaper
    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}