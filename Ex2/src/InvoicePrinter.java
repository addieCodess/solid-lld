public class InvoicePrinter {

    public void print(String invoiceText) {
        System.out.print(invoiceText);
    }

    public void printSaved(String invId, int lines) {
        System.out.println("Saved invoice: " + invId + " (lines=" + lines + ")");
    }
}
