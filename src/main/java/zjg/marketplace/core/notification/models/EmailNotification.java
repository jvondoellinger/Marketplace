package zjg.marketplace.core.notification.models;

public class EmailNotification extends Notification {
    private String title;
    private String subject;
    private String target;

    public String getTitle() {
        return title;
    }
    public String getSubject() {
        return subject;
    }
    public String getTarget() {
        return target;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setTarget(String target) {
        this.target = target;
    }
}
