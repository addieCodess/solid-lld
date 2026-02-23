public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {

        if (req == null)
            throw new IllegalArgumentException("request cannot be null");

        String title = req.title == null ? "" : req.title;
        String body = req.body == null ? "" : req.body;

        byte[] data = encode(title, body);
        return new ExportResult(contentType(), data);
    }

    protected abstract byte[] encode(String title, String body);
    protected abstract String contentType();
}
