package zjg.marketplace.application.mapper;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import zjg.marketplace.core.image.entity.BasicImageFile;
import zjg.marketplace.core.image.entity.ImageFileFactory;

import java.util.List;

@Service
public class ImageMapper extends BinaryMapper {
    public Flux<BasicImageFile> toImageFile(List<FilePart> fileParts, String productId) {
        return Flux.fromIterable(fileParts).flatMap(file ->
                toByteArray(file)
                        .map(bytes -> ImageFileFactory.factory(bytes, file.filename())));
    }
}