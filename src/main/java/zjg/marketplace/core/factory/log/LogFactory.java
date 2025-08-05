package zjg.marketplace.core.factory.log;

import zjg.marketplace.core.valueObjects.log.Log;
import zjg.marketplace.core.enums.LogLevel;

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
