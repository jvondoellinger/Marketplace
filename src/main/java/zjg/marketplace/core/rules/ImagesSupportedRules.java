package zjg.marketplace.core.rules;

import zjg.marketplace.core.exceptions.file.UnsupportedFileException;
import zjg.marketplace.core.utils.PathUtils;

import java.util.List;

public class ImagesSupportedRules {
    private static final String EXTENSION_NOT_SUPPORTED_MESSAGE = "The provided file isn't supported! Please, send a valid image file!";
    private static final List<String> supportedExtensions = getSupportedExtensions();

    public static boolean isSupported(String str) {
        var result = PathUtils.getExtension(str);
        return supportedExtensions.contains(result);
    }
    public static void throwIfNotSupported(String str) throws UnsupportedFileException {
        var result = PathUtils.getExtension(str);
        if(!supportedExtensions.contains(result)) {
            throw new UnsupportedFileException(EXTENSION_NOT_SUPPORTED_MESSAGE);
        }
    }

    private static List<String> getSupportedExtensions() {
        return List.of(".jpeg",
                ".tif",
                ".jpg",
                ".bmp",
                ".avif",
                ".heif",
                ".heic",
                ".webp",
                ".tiff",
                ".png");
    }
}
