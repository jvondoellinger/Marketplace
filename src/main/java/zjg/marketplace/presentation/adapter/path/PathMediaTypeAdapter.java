package zjg.marketplace.presentation.adapter.path;

import org.springframework.http.MediaType;
import zjg.marketplace.core.valueObject.path.ImagePath;
import zjg.marketplace.core.valueObject.extension.ImageExtension;

public class PathMediaTypeAdapter extends ImagePath {
    public PathMediaTypeAdapter(String path) {
        this.setPath(path);
    }
    public MediaType getMediaType() {
        var extension = new ImageExtension(getPath());
        var fixedExt = extension.getExtension().replace(".", "");
        var formated = String.format("image/%s", fixedExt);
        return MediaType.valueOf(formated);
    }
}
