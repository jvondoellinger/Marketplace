package zjg.marketplace.core.mediator;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.payment.PixPayment;
import zjg.marketplace.core.interfaces.payment.pix.IPixPaymentMethodService;

public class OrderToPaymentMediator {
    private final IPixPaymentMethodService service;
    public OrderToPaymentMediator(IPixPaymentMethodService service) {
        this.service = service;
    }

    public Mono<PixPayment> closeOrder(Order order) {
        return service.generateQrCodeToOrder(order);
    }
}