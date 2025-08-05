package zjg.marketplace.core.events.models;

import java.util.Date;

public abstract class AuditEventModel {
    private final Date occurredOn;
    private Class<?> origin;
    // Constructor -----------------------------------
    public AuditEventModel(Class<?> origin) {
        this.origin = origin;
        this.occurredOn = new Date();
    }

    // Getter ----------------------------------------
    public Date getOccurredOn() {
        return occurredOn;
    }
    public Class<?> getOrigin() {
        return origin;
    }

    // Setter -----------------------------------------
    public void setOrigin(Class<?> origin) {
        this.origin = origin;
    }
}
