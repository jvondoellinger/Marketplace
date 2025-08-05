package zjg.marketplace.core.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
    public static boolean isGreaterThanOne(BigDecimal source) {
        return source.compareTo(BigDecimal.ONE) > 0;
    }
}
