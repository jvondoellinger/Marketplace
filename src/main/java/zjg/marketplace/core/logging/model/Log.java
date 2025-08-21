package zjg.marketplace.core.logging.model;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.logging.enums.LogLevel;

@BadCode
// Mudar a classe de package, pois não se trata de uma entidade!
public class Log {
    // Properties -----------------------------------------------------
    private Class<?> origin;
    private String message;
    private String root;
    private LogLevel level;

    // Getter --------------------------------------------------------
    public Class<?> getOrigin() {
        return origin;
    }
    public String getMessage() {
        return message;
    }
    public String getRoot() {
        return root;
    }
    public LogLevel getLevel() {
        return level;
    }

    // Setter --------------------------------------------------------
    public void setOrigin(Class<?> origin) {
        this.origin = origin;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setRoot(String root) {
        this.root = root;
    }
    public void setLevel(LogLevel level) {
        this.level = level;
    }
}
