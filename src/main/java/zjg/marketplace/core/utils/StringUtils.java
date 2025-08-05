package zjg.marketplace.core.utils;

public class StringUtils {
    public static boolean blankOrNull(String str) {
        if (str == null) return true;
        if (str.isBlank()) return true;
        return false;
    }
}
