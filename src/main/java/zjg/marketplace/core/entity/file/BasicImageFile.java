package zjg.marketplace.core.entity.file;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.field.path.CustomPath;

// Invalid Package
@BadCode
public class BasicImageFile {
    // Constructors ----------------------------------
    public BasicImageFile() {}
    public BasicImageFile(byte[] binary, CustomPath path) {
        this.path = path;
        this.binary = binary;
    }

    // Properties ----------------------------------
    private byte[] binary;
    private CustomPath path;

    // Getter ----------------------------------
    public CustomPath getPath() {
        return path;
    }
    public byte[] getBinary() {
        return binary;
    }

    // Setter ----------------------------------
    public void setPath(CustomPath filename) {
        this.path = filename;
    }
    public void setBinary(byte[] binary) {
        this.binary = binary;
    }
}
