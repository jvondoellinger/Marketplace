package zjg.marketplace.core.interfaces.payment.pix;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.payment.PixPayment;

public interface IPixPaymentMethodService {
    Mono<PixPayment> generateQrCodeToOrder(Order payment);
}