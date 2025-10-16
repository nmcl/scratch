import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONObject;

public class OllamaJavaExample2 {

    public static void main(String[] args) throws Exception {
        String ollamaUrl = "http://localhost:11434/api/generate";
        String model = "gemma3";
        String prompt = "What's the capital of France?";

        String jsonBody = String.format("""
            {
              "model": "%s",
              "prompt": "%s",
              "stream": false
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

        int statusCode = resp.statusCode();
        String responseBody = resp.body();

        System.out.println("== Ollama Java Client ==");
        System.out.println("Model: " + model);
        System.out.println("Prompt: " + prompt);
        System.out.println("Status: " + statusCode);

        if (statusCode == 200) {
            JSONObject json = new JSONObject(responseBody);
            String responseText = json.optString("response", "No response found");
            System.out.println("\n🧠 Ollama Response:\n" + responseText);
        } else {
            System.out.println("Error: " + responseBody);
        }
    }

    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}