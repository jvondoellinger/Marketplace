package zjg.marketplace.core.image.entity;

import zjg.marketplace.core.image.valueObj.path.ImagePathFactory;

public class ImageFileFactory {
    public static BasicImageFile factory(byte[] binary, String completePath) {
        var paths = ImagePathFactory.factory(completePath);
        var image = new BasicImageFile();
        image.setPath(paths);
        image.setBinary(binary);
        return image;
    }
}
