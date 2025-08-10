package zjg.marketplace.core.valueObjects.path;

import zjg.marketplace.core.rules.ImagesSupportedRules;
import zjg.marketplace.core.utils.PathUtils;

public class ImagePath {
    // * Constructor ------------------------------------
    public ImagePath(String completePath) {
        this.completePath = completePath;
    }

    private String completePath;

    // * Getter ----------------------------------------
    public String getCompletePath() {
        return completePath;
    }

    // * Setter ----------------------------------------
    protected void setCompletePath(String completePath) {
        this.completePath = completePath;
    }

    // * Custom Getter ---------------------------------
    public String getExtension() {
        return PathUtils.getExtension(completePath);
    }
}
