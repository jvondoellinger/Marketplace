package zjg.marketplace.core.factory.path;

import zjg.marketplace.core.rules.ImagesSupportedRules;
import zjg.marketplace.core.valueObjects.path.ImagePath;
import zjg.marketplace.core.valueObjects.path.ImagePaths;

import java.util.ArrayList;
import java.util.List;

public class ImagePathsFactory {
    public static ImagePaths factory(List<String> completePath) {
        List<ImagePath> paths = new ArrayList<>();
        for (var cp : completePath){
            ImagesSupportedRules.throwIfNotSupported(cp);
            var path = new ImagePath(cp);
            paths.add(path);
        }
        return new ImagePaths(paths);
    }
}
