package zjg.marketplace.presentation.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class ErrorResponse {
    public ErrorResponse(String message) {
        this.message = message;
    }

    private String id = UUID.randomUUID().toString();

    private LocalDateTime occurredAt = LocalDateTime.now();
    private String message;

    public String getMessage() {
        return message;
    }
    public String getId() {
        return id;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
