import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {

    protected String contentType() {
        return "application/pdf";
    }

    protected byte[] encode(String title, String body) {
        if (body.length() > 20)
            throw new RuntimeException("PDF cannot handle content > 20 chars");

        String fakePdf = "PDF(" + title + "):" + body;
        return fakePdf.getBytes(StandardCharsets.UTF_8);
    }
}
