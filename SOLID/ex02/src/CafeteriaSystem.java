import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PricingCalculator pricing = new PricingCalculator();
    private final TaxPolicy taxPolicy = new DefaultTaxPolicy();
    private final DiscountPolicy discountPolicy = new DefaultDiscountPolicy();
    private final InvoiceRepository store = new FileStore();
    private final InvoicePrinter printer = new InvoicePrinter();

    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        // line items
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        double subtotal = pricing.subtotal(lines, menu);
        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(customerType, subtotal, lines);
        double total = subtotal + tax - discount;

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        String printable = InvoiceFormatter.identityFormat(out.toString());

        printer.print(printable);
        store.save(invId, printable);
        printer.printSaved(invId, store.countLines(invId));
    }
}
