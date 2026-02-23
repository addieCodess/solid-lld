import java.util.*;

public class DefaultDiscountPolicy implements DiscountPolicy {
    public double discountAmount(String customerType, double subtotal, List<OrderLine> lines) {
        return DiscountRules.discountAmount(customerType, subtotal, lines.size());
    }
}
