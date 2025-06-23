package zjg.marketplace.infrastructure.storage.impl;

import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import zjg.marketplace.anotation.BadCode;
import zjg.marketplace.core.interfaces.storage.StorageService;
import zjg.marketplace.core.utils.PathUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

//@Service
public class LocalStorageService implements StorageService {
    private static final Path path = Paths.get("uploads");
    @Override
    public Mono<String> upload(FilePart file, String fileName) {
        return DataBufferUtils.join(file.content())
                .publishOn(Schedulers.boundedElastic())
                .map(dataBuffer -> {
                    try {
                        if (!Files.exists(path)) {
                            Files.createDirectories(path);
                        }
                        var oldName = Objects.requireNonNull(file.filename());
                        var newName = PathUtils.modifyName(oldName, fileName);
                        var out = path.resolve(newName);

                        ByteBuffer buffer = dataBuffer.asByteBuffer().asReadOnlyBuffer();
                        InputStream inputStream = new ByteBufferBackedInputStream(buffer);
                        Files.copy(inputStream, out, StandardCopyOption.REPLACE_EXISTING);

                        return out.normalize().toString()   ;
                    } catch (IOException e) {
                        throw new RuntimeException("Erro ao salvar arquivo", e);
                    } finally {
                        DataBufferUtils.release(dataBuffer); // Liberando o buffer
                    }
                });
    }

    @Override
    public Mono<Void> remove(Resource file) {
        return null;
    }

    @BadCode
    @Override
    public Mono<byte[]> get(String name) {
        try{
            Resource resource = new FileSystemResource(path.resolve(name));
            return Mono.just(resource.getContentAsByteArray());
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Arquivo não encontrado ou não acessível"));
        }
    }
}
