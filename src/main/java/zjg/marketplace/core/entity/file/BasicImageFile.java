package zjg.marketplace.core.entity.file;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.factory.file.ImageFileFactory;
import zjg.marketplace.core.valueObject.path.ImagePath;

// Invalid Package
@BadCode
public class BasicImageFile {
    // Constructors ----------------------------------
    public BasicImageFile() {}
    public BasicImageFile(byte[] binary, ImagePath path) {
        this.path = path;
        this.binary = binary;
    }

    // Properties ----------------------------------
    private byte[] binary;
    private ImagePath path;

    // Getter ----------------------------------
    public ImagePath getPath() {
        return path;
    }
    public byte[] getBinary() {
        return binary;
    }

    // Setter ----------------------------------
    public void setPath(ImagePath filename) {
        this.path = filename;
    }
    public void setBinary(byte[] binary) {
        this.binary = binary;
    }

    public static BasicImageFile getInstance(byte[] binary, ImagePath path) {
        var image = new BasicImageFile();
        image.setBinary(binary);
        image.setPath(path);
        return image;
    }
}
