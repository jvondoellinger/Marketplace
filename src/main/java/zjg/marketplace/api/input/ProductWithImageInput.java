package zjg.marketplace.api.input;

import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;

public class ProductWithImageInput extends ProductInput {
    private FilePart file; // Mudar para FilePart

    public FilePart getFile() {
        return file;
    }

    public void setFile(FilePart file) {
        this.file = file;
    }
}
