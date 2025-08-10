package zjg.marketplace.core.factory.mediator;

import zjg.marketplace.core.entity.notification.EmailNotification;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.notification.INotificationSender;
import zjg.marketplace.core.interfaces.services.payment.pix.IPixPaymentMethodService;
import zjg.marketplace.core.interfaces.services.repository.Repository;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.mediator.OrderToPaymentMediator;

public class OrderToPaymentMediatorFactory {
    private OrderToPaymentMediatorFactory() {}

    public static OrderToPaymentMediator factory(IPixPaymentMethodService service,
                                                 CommandRepository<Order> repository,
                                                 INotificationSender<EmailNotification> notificationSender) {
        return new OrderToPaymentMediator(repository, service, notificationSender);
    }
}
