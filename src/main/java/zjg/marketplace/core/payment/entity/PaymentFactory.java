package zjg.marketplace.core.factory.payment;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.entity.payment.PixPayment;

public class PaymentFactory {
    public static PixPayment factoryPix(Order order, String code, String qrCodeBase64) {
        return new PixPayment(order, qrCodeBase64, code);
    }
}
