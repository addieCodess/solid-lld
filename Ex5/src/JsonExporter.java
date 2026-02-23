import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {

    protected String contentType() {
        return "application/json";
    }

    protected byte[] encode(String title, String body) {
        String json = "{\"title\":\"" + escape(title) + "\",\"body\":\"" + escape(body) + "\"}";
        return json.getBytes(StandardCharsets.UTF_8);
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }
}
