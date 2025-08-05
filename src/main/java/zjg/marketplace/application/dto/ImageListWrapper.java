package zjg.marketplace.application.dto;

import org.springframework.http.codec.multipart.FilePart;

import java.util.List;

public class ImageListWrapper {
    private List<FilePart> files;

    public List<FilePart> getFiles() {
        return files;
    }

    public void setFiles(List<FilePart> files) {
        this.files = files;
    }
}
