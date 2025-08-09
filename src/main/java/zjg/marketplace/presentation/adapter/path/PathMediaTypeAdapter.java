package zjg.marketplace.presentation.adapter.path;

import org.springframework.http.MediaType;
import zjg.marketplace.core.valueObjects.path.ImagePath;

public class PathMediaTypeAdapter extends ImagePath {
    public PathMediaTypeAdapter(String completePath) {
        super(completePath);
    }

    public MediaType getMediaType() {
        return MediaType.valueOf("image/%s".formatted(getExtension()));
    }
}
