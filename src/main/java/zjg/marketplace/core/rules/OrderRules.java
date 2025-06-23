package zjg.marketplace.core.rules;

import zjg.marketplace.core.entity.order.Order;

public class OrderRules {
    private OrderRules() {}

    public static boolean canUpdate(Order order) {
        return switch (order.getStatus()) {
            case CANCELED, PAID, REFUNDED -> false;
            default -> true;
        };
    }
    public static void throwIfCannotUpdate(Order order) {
        if(!canUpdate(order)) throw new IllegalArgumentException("This order cannot be updated!");
    }
}
