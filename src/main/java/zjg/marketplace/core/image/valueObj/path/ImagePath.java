package zjg.marketplace.core.image.valueObj.path;

import zjg.marketplace.core.image.utils.PathUtils;

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
