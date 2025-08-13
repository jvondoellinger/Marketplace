package zjg.marketplace.core.factory.path;

import zjg.marketplace.core.image.rules.ImagesSupportedRules;
import zjg.marketplace.core.image.valueObj.path.ImagePath;
import zjg.marketplace.core.image.valueObj.path.ImagePathList;

import java.util.ArrayList;
import java.util.List;

public class ImagePathsFactory {
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
