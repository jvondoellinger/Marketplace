package zjg.marketplace.core.utils;

import java.util.Objects;

public class UpdaterStrategyUtils {
    public static <T, Y> void throwIfNullSourceOrTarget(T target, Y source) {
        if(Objects.isNull(target)) throw new NullPointerException("User target cannot be null!");
        if(Objects.isNull(source)) throw new NullPointerException("User source cannot be null!");
    }
}
