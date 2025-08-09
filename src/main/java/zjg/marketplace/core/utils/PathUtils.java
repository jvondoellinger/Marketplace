package zjg.marketplace.core.utils;

import zjg.marketplace.core.anotation.BadCode;

public class PathUtils {
    private PathUtils() {

    }
    @BadCode
    public static String modifyName(String completeName, String newName) {
        var ext = getExtension(completeName);
        var newExt = newName.lastIndexOf(".");
        if(newExt != -1)
            return newName.substring(0, newExt) + ext;
        return newName + ext;
    }
    public static String removeBars(String path) {
        return path.replaceAll("/", "");
    }

    public static String getExtension(String str) {
        return str.replaceAll(".*(\\.[^\\.]+)$", "$1");
    }
}
