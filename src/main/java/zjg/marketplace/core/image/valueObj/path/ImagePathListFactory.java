package zjg.marketplace.core.image.valueObj.path;

import zjg.marketplace.core.image.rules.ImagesSupportedRules;

import java.util.ArrayList;
import java.util.List;

public class ImagePathListFactory {
    public static ImagePathList factory(List<String> completePath) {
        List<ImagePath> paths = new ArrayList<>();
        for (var cp : completePath){
            ImagesSupportedRules.throwIfNotSupported(cp);
            var path = new ImagePath(cp);
            paths.add(path);
        }
        return new ImagePathList(paths);
    }
}
