package zjg.marketplace.core.factory.path;

import zjg.marketplace.core.rules.ImagesSupportedRules;
import zjg.marketplace.core.valueObjects.path.ImagePath;

public class ImagePathFactory {
    public static ImagePath factory(String completePath) {
        ImagesSupportedRules.throwIfNotSupported(completePath);
        return new ImagePath(completePath);
    }
}
