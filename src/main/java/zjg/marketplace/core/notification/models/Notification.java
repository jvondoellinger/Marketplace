package zjg.marketplace.core.notification.models;

public abstract class Notification {
    public String content;

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

}
