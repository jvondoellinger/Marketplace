package zjg.marketplace.core.image.valueObj.path;

import zjg.marketplace.core.image.rules.ImagesSupportedRules;

public class ImagePathFactory {
    public static ImagePath factory(String completePath) {
        ImagesSupportedRules.throwIfNotSupported(completePath);
        return new ImagePath(completePath);
    }
}
