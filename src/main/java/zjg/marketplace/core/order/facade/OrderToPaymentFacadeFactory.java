package zjg.marketplace.core.factory.mediator;

import zjg.marketplace.core.notification.models.EmailNotification;
import zjg.marketplace.core.notification.services.INotificationSender;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.facade.OrderPaymentProcessor;
import zjg.marketplace.core.payment.services.IPixPaymentProcessor;

public class OrderToPaymentFacadeFactory {
    private OrderToPaymentFacadeFactory() {}

    public static OrderPaymentProcessor factory(IPixPaymentProcessor service,
                                                CommandRepository<Order> repository,
                                                INotificationSender<EmailNotification> notificationSender) {
        return new OrderPaymentProcessor(repository, service, notificationSender);
    }
}
