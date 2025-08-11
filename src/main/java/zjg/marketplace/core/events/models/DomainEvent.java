package zjg.marketplace.core.events.models;

import java.util.concurrent.CompletableFuture;

public interface DomainEvent {
    void publish(DomainEvent event);
    CompletableFuture<Void> runAsync();
}
