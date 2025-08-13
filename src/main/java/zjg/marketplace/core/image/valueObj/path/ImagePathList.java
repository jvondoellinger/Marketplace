package zjg.marketplace.core.image.valueObj.path;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ImagePathComposite {
    private final List<ImagePath> paths;

    public ImagePathComposite(List<ImagePath> paths) {
        this.paths = new CopyOnWriteArrayList<>(paths);
    }

    // * Add ---------------------------------------
    public synchronized void add(ImagePath path) {
        paths.add(path);
    }
    public synchronized void add(List<ImagePath> paths) {
        this.paths.addAll(paths);
    }

    // * Remove ---------------------------------------
    public synchronized void remove(ImagePath path) {
        paths.remove(path);
    }
    public synchronized void remove(List<ImagePath> paths) {
        this.paths.removeAll(paths);
    }

    // * Getter ---------------------------------------
    public synchronized List<ImagePath> getAll() {
        return paths;
    }
}
