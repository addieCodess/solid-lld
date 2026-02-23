import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    protected String contentType() {
        return "text/csv";
    }

    protected byte[] encode(String title, String body) {
        String cleanBody = body.replace("\n", " ").replace(",", " ");
        String csv = "title,body\n" + title + "," + cleanBody + "\n";
        return csv.getBytes(StandardCharsets.UTF_8);
    }
}
