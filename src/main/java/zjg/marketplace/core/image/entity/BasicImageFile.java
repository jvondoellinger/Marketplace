package zjg.marketplace.core.image.entity;

import zjg.marketplace.core.image.valueObj.path.ImagePath;

public class BasicImageFile {
    // * Constructors ----------------------------------
    public BasicImageFile() {}
    public BasicImageFile(byte[] binary, ImagePath path) {
        this.path = path;
        this.binary = binary;
    }

    // * Properties ----------------------------------
    private byte[] binary;
    private ImagePath path;

    // * Getter ----------------------------------
    public ImagePath getPath() {
        return path;
    }
    public byte[] getBinary() {
        return binary;
    }

    // * Setter ----------------------------------
    public void setPath(ImagePath filename) {
        this.path = filename;
    }
    public void setBinary(byte[] binary) {
        this.binary = binary;
    }
}
