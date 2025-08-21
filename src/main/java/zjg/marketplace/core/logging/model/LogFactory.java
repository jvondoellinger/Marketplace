package zjg.marketplace.core.logging.model;

import zjg.marketplace.core.logging.enums.LogLevel;

public class LogFactory {
    private LogFactory() {}

    public static Log factory(LogLevel level, Class<?> origin, String message, String root) {
        var log = new Log();
        log.setLevel(level);
        log.setMessage(message);
        log.setOrigin(origin);
        log.setRoot(root);
        return log;
    }

}
