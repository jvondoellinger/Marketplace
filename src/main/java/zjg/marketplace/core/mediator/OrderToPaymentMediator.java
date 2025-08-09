package zjg.marketplace.core.mediator;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.entity.notification.EmailNotification;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.payment.PixPayment;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.enums.OrderStatusEnum;
import zjg.marketplace.core.interfaces.services.notification.INotificationSender;
import zjg.marketplace.core.interfaces.services.payment.pix.IPixPaymentMethodService;
import zjg.marketplace.core.interfaces.services.repository.Repository;

public class OrderToPaymentMediator {
    private final IPixPaymentMethodService service;
    private final Repository<Order> repository;
    private final INotificationSender<EmailNotification> notificationSender;
    public OrderToPaymentMediator(Repository<Order> repository,
                                  IPixPaymentMethodService service,
                                  INotificationSender<EmailNotification> notificationSender) {
        this.service = service;
        this.repository = repository;
        this.notificationSender = notificationSender;
    }
    @BadCode
    public Mono<PixPayment> orderToPayment(Order order, User payer) {
        order.updateStatus(OrderStatusEnum.WAITING_PAYMENT);
        return repository.update(order)
                .flatMap(updated -> service.generateQrCodeToOrder(updated, payer));

    }
}