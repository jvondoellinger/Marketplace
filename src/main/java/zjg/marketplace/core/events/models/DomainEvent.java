package zjg.marketplace.core.events.models;

import java.util.concurrent.CompletableFuture;

public interface DomainEvent {
    void run();
    CompletableFuture<Void> runAsync();
}
