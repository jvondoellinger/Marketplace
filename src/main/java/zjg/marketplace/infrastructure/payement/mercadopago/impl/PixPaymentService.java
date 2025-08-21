package zjg.marketplace.infrastructure.payement.mercadopago.impl;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.payment.entity.PixPayment;
import zjg.marketplace.core.payment.entity.PaymentFactory;
import zjg.marketplace.core.payment.services.IPixPaymentProcessor;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.infrastructure.payement.mercadopago.factory.MercadoPagoPaymentFactory;

@Service
public class PixPaymentService implements IPixPaymentProcessor {
    @Override
    public Mono<PixPayment> generateQrCodeToOrder(Order order, User payer) {
        return Mono.fromSupplier(() -> {
            var mp_payment = MercadoPagoPaymentFactory.factoryPixPayment(payer, order);
            var data = mp_payment.getPointOfInteraction().getTransactionData();
            return PaymentFactory.factoryPix(order, data.getQrCode(), data.getQrCodeBase64());
        });
    }
}
