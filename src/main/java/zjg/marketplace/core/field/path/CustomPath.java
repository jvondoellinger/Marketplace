package zjg.marketplace.core.field.path;

import zjg.marketplace.core.field.extension.ImageExtension;
import zjg.marketplace.core.utils.PathUtils;

import java.time.Instant;

public class CustomPath {

    // Constructors ----------------
    protected CustomPath() {}
    public CustomPath(ImageExtension imageExtension) {
        this.extension = imageExtension;
    }
    public CustomPath(ImageExtension imageExtension, String id) {
        this.extension = imageExtension;
        generatePath(id);
    }

    // Properties -----------------
    private ImageExtension extension;
    private String path;

    // Getter ----------------------
    public String getPath() {
        return this.path;
    }
    public ImageExtension getExtension() {
        return this.extension;
    }

    // Setter ----------------------
    protected void setPath(String path) {
        this.path = path;
    }
    protected void setExtension(ImageExtension extension) {
        this.extension = extension;
    }

    // Custom methods -------------
    public void generatePath(String id) {
        var now = Instant.now().toString();
        var ext = PathUtils.getExtension(extension.getExtension());
        this.path = String.format("%s_%s%s", now, id, ext);
    }
}
