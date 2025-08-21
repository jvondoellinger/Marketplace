package zjg.marketplace.core.payment.services;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.payment.entity.PixPayment;
import zjg.marketplace.core.user.entity.User;

public interface IPixPaymentProcessor {
    Mono<PixPayment> generateQrCodeToOrder(Order payment, User payer);
}