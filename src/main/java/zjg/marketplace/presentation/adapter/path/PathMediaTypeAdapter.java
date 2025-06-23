package zjg.marketplace.presentation.adapter.path;

import org.springframework.http.MediaType;
import zjg.marketplace.core.field.path.CustomPath;
import zjg.marketplace.core.field.extension.ImageExtension;

public class PathMediaTypeAdapter extends CustomPath {
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
