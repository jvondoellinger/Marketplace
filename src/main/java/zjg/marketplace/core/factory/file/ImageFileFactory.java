package zjg.marketplace.core.factory.file;

import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.field.path.CustomPath;
import zjg.marketplace.core.field.extension.ImageExtension;

public class ImageFileFactory {
    public static BasicImageFile factory(byte[] binary, String containsExtension, String productId) {
        var image = new BasicImageFile();
        var extension = new ImageExtension(containsExtension);
        var path = new CustomPath(extension);
        path.generatePath(productId);
        image.setBinary(binary);
        image.setPath(path);
        return image;
    }
}
