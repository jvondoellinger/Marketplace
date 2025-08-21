package zjg.marketplace.core.notification.services;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.notification.models.Notification;

public interface INotificationSender<TNotification extends Notification> {
    Mono<Void> send(TNotification notification);
}
