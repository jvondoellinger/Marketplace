package zjg.marketplace.core.interfaces.services.notification;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.notification.Notification;

public interface INotificationSender<TNotification extends Notification> {
    Mono<Void> send(TNotification notification);
}
