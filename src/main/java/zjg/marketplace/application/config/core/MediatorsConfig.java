package zjg.marketplace.application.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zjg.marketplace.core.notification.models.EmailNotification;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.facade.OrderToPaymentFacadeFactory;
import zjg.marketplace.core.notification.services.INotificationSender;
import zjg.marketplace.core.payment.services.IPixPaymentProcessor;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.order.facade.OrderPaymentProcessor;

@Configuration
public class MediatorsConfig {
    private final CommandRepository<Order> command;
    private final IPixPaymentProcessor storageService;
    private final INotificationSender<EmailNotification> notificationSender;
    public MediatorsConfig(CommandRepository<Order> command,
                           IPixPaymentProcessor storageService,
                           INotificationSender<EmailNotification> notificationSender) {
        this.command = command;
        this.storageService = storageService;
        this.notificationSender = notificationSender;
    }

    @Bean
    public OrderPaymentProcessor orderToPaymentMediator() {
        return OrderToPaymentFacadeFactory.factory(storageService, command, notificationSender);
    }
}
