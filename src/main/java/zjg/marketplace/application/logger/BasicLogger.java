package zjg.marketplace.application.logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BasicLogger {

    public static <T> void error(Class<T> origin, String message) {
        LoggerFactory.getLogger(origin).error(message);
    }
    public static <T> void error(Class<T> origin, String message, Object... values) {
        LoggerFactory.getLogger(origin).error(message, values);
    }

    public static <T> void warning(Class<T> origin, String message) {
        LoggerFactory.getLogger(origin).warn(message);
    }
    public static <T> void warning(Class<T> origin, String message, Object... values) {
        LoggerFactory.getLogger(origin).warn(message, values);
    }

    public static <T> void info(Class<T> origin, String message) {
        LoggerFactory.getLogger(origin).info(message);
    }
    public static <T> void info(Class<T> origin, String message, Object... values) {
        LoggerFactory.getLogger(origin).info(message, values);
    }
}
