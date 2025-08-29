package zjg.marketplace.core.order.facade;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.notification.models.EmailNotification;
import zjg.marketplace.core.notification.services.INotificationSender;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.payment.entity.PixPayment;
import zjg.marketplace.core.payment.services.IPixPaymentProcessor;
import zjg.marketplace.core.user.entity.User;

public class OrderPaymentProcessor {
    private final IPixPaymentProcessor service;
    private final CommandRepository<Order> command;
    private final INotificationSender<EmailNotification> notificationSender;
    public OrderPaymentProcessor(CommandRepository<Order> command,
                                 IPixPaymentProcessor service,
                                 INotificationSender<EmailNotification> notificationSender) {
        this.service = service;
        this.command = command;
        this.notificationSender = notificationSender;
    }

    public Mono<PixPayment> orderToPayment(Order order, User payer) {
      order.waitingPayment();
      return command.update(order)
          .flatMap(updated -> service.generateQrCodeToOrder(updated, payer))
          .flatMap(x -> {
              var notification = new EmailNotification();
              notification.setTarget(payer.getEmail());
              notification.setSubject("ORDER CLOSED - PAY TO YOURS PRODUCTS BE SENDED");
              notification.setContent("PIX CODE: %s".formatted(x.getCode()));
              return notificationSender.send(notification).thenReturn(x);
          });

    }
}