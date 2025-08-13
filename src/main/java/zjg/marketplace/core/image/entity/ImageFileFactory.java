package zjg.marketplace.core.factory.file;

import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.factory.path.ImagePathFactory;

public class ImageFileFactory {
    public static BasicImageFile factory(byte[] binary, String completePath) {
        var paths = ImagePathFactory.factory(completePath);
        var image = new BasicImageFile();
        image.setPath(paths);
        image.setBinary(binary);
        return image;
    }
}
