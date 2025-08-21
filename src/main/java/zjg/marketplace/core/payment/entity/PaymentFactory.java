package zjg.marketplace.core.payment.entity;

import zjg.marketplace.core.order.entity.Order;

public class PaymentFactory {
    public static PixPayment factoryPix(Order order, String code, String qrCodeBase64) {
        return new PixPayment(order, qrCodeBase64, code);
    }
}
