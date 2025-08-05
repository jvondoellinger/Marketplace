package zjg.marketplace.application.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import zjg.marketplace.core.entity.notification.EmailNotification;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.factory.mediator.OrderToPaymentMediatorFactory;
import zjg.marketplace.core.interfaces.services.notification.INotificationSender;
import zjg.marketplace.core.interfaces.services.payment.pix.IPixPaymentMethodService;
import zjg.marketplace.core.interfaces.services.repository.IRepository;
import zjg.marketplace.core.mediator.OrderToPaymentMediator;

@Component
public class MediatorsConfig {
    private final IRepository<Order> orderRepository;
    private final IPixPaymentMethodService storageService;
    private final INotificationSender<EmailNotification> notificationSender;
    public MediatorsConfig(IRepository<Order> orderRepository,
                           IPixPaymentMethodService storageService, INotificationSender<EmailNotification> notificationSender) {
        this.orderRepository = orderRepository;
        this.storageService = storageService;
        this.notificationSender = notificationSender;
    }

    @Bean
    public OrderToPaymentMediator getOrderRepository() {
        return OrderToPaymentMediatorFactory.factory(storageService, orderRepository, notificationSender);
    }
}
