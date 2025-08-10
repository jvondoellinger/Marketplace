package zjg.marketplace.core.mediator;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.entity.notification.EmailNotification;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.order.OrderBuilder;
import zjg.marketplace.core.entity.payment.PixPayment;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.enums.OrderStatusEnum;
import zjg.marketplace.core.factory.chain.updater.OrderUpdaterHandleFactory;
import zjg.marketplace.core.interfaces.services.notification.INotificationSender;
import zjg.marketplace.core.interfaces.services.payment.pix.IPixPaymentMethodService;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;

public class OrderToPaymentMediator {
    private final IPixPaymentMethodService service;
    private final CommandRepository<Order> command;
    private final INotificationSender<EmailNotification> notificationSender;
    public OrderToPaymentMediator(CommandRepository<Order> command,
                                  IPixPaymentMethodService service,
                                  INotificationSender<EmailNotification> notificationSender) {
        this.service = service;
        this.command = command;
        this.notificationSender = notificationSender;
    }
    @BadCode
    public Mono<PixPayment> orderToPayment(Order order, User payer) {
        System.out.println("???");
        var updateHandler = OrderUpdaterHandleFactory.factory();
        var unsafe = OrderBuilder.builder().status(OrderStatusEnum.WAITING_PAYMENT).build();
        updateHandler.handle(order, unsafe);
        return command.update(order)
                .flatMap(updated -> {
                    var s = service.generateQrCodeToOrder(updated, payer);
                    var n = new EmailNotification();
                    n.setTarget(payer.getEmail());
                    n.setTitle("ORDER CLOSED - WAITING PAYMENT");
                    n.setSubject("test");
                    n.setContent("test");
                    System.out.println("Aqui?");
                    return notificationSender.send(n).then(s);
                });

    }
}