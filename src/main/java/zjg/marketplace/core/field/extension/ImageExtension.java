package zjg.marketplace.core.field.extension;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.utils.PathUtils;

@BadCode
public class ImageExtension {
    public ImageExtension() {}
    public ImageExtension(String containsExtension) {
        resolveExtension(containsExtension);
    }

    private String extension;

    // Getter
    public String getExtension() {
        return extension;
    }

    // Setter
    public void setExtension(String extension) {
        if(!validExtension(extension)) {
            throw new IllegalArgumentException("This file extension isn't supported!");
        }
        this.extension = extension;
    }

    // Custom
    public void resolveExtension(String filename) {
        var capturedExt = PathUtils.getExtension(filename);
        setExtension(capturedExt);
    }

    private Boolean validExtension(String extension) {
        return switch (extension) {
            case ".jpeg" -> true;
            case ".jpg" -> true;
            case ".png" -> true;
            case ".tiff" -> true;
            case ".tif" -> true;
            case ".webp" -> true;
            case ".heic" -> true;
            case ".heif" -> true;
            case ".avif" -> true;
            case ".bmp" -> true;
            default -> false;
        };
    }
}
