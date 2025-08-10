package zjg.marketplace.application.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import zjg.marketplace.core.entity.notification.EmailNotification;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.factory.mediator.OrderToPaymentMediatorFactory;
import zjg.marketplace.core.interfaces.services.notification.INotificationSender;
import zjg.marketplace.core.interfaces.services.payment.pix.IPixPaymentMethodService;
import zjg.marketplace.core.interfaces.services.repository.Repository;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.mediator.OrderToPaymentMediator;

@Configuration
public class MediatorsConfig {
    private final CommandRepository<Order> command;
    private final IPixPaymentMethodService storageService;
    private final INotificationSender<EmailNotification> notificationSender;
    public MediatorsConfig(CommandRepository<Order> command,
                           IPixPaymentMethodService storageService,
                           INotificationSender<EmailNotification> notificationSender) {
        this.command = command;
        this.storageService = storageService;
        this.notificationSender = notificationSender;
    }

    @Bean
    public OrderToPaymentMediator orderToPaymentMediator() {
        return OrderToPaymentMediatorFactory.factory(storageService, command, notificationSender);
    }
}
