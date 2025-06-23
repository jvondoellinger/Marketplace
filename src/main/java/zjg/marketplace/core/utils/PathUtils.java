package zjg.marketplace.core.utils;

import zjg.marketplace.anotation.BadCode;

public class PathUtils {
    private PathUtils() {

    }
    @BadCode
    public static String modifyName(String completeName, String newName) {
        var last = completeName.lastIndexOf(".");
        if(last == -1) {
            throw new RuntimeException("File name doesn't have an extension");
        }
        var ext = completeName.substring(last);
        var newExt = newName.lastIndexOf(".");
        if(newExt != -1) {
            return newName.substring(0, newExt) + ext;
        }
        return newName + ext;
    }
}
