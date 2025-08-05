package zjg.marketplace.infrastructure.payement.mercadopago.impl;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.payment.PixPayment;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.factory.payment.PaymentFactory;
import zjg.marketplace.core.interfaces.services.payment.pix.IPixPaymentMethodService;
import zjg.marketplace.infrastructure.payement.mercadopago.factory.MercadoPagoPaymentFactory;

@Service
public class PixPaymentService implements IPixPaymentMethodService {
    @Override
    public Mono<PixPayment> generateQrCodeToOrder(Order order, User payer) {
        return Mono.fromSupplier(() -> {
            var mp_payment = MercadoPagoPaymentFactory.factoryPixPayment(payer, order);
            var data = mp_payment.getPointOfInteraction().getTransactionData();
            return PaymentFactory.factoryPix(order, data.getQrCode(), data.getQrCodeBase64());
        });
    }
}
