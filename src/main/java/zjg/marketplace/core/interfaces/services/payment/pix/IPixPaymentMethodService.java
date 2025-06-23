package zjg.marketplace.core.interfaces.services.payment.pix;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.payment.PixPayment;
import zjg.marketplace.core.entity.user.User;

public interface IPixPaymentMethodService {
    Mono<PixPayment> generateQrCodeToOrder(Order payment, User payer);
}