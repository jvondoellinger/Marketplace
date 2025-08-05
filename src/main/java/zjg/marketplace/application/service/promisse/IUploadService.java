package zjg.marketplace.application.service.promisse;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.file.BasicImageFile;
import java.util.List;
import java.util.function.Supplier;

public interface IUploadService<Entity> {
    Mono<BasicImageFile> upload(FilePart file, String id);
    Flux<BasicImageFile> upload(List<FilePart> file, String id);
    Mono<BasicImageFile> upload(FilePart files, Supplier<Entity> supplier);
    Flux<BasicImageFile> upload(List<FilePart> files, Supplier<Entity> supplier);
}
