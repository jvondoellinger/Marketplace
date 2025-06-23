package zjg.marketplace.presentation.input;

import org.springframework.http.codec.multipart.FilePart;

import java.util.List;

public class ProductWithImageInput extends ProductInput {
    private List<FilePart> file; // Mudar para FilePart

    public List<FilePart> getFiles() {
        return file;
    }

    public void setFile(List<FilePart> file) {
        this.file = file;
    }
}
