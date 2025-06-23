package zjg.marketplace.application.mapper;

import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BinaryMapper {
    public Mono<byte[]> toByteArray(FilePart filePart) {
        return DataBufferUtils.join(filePart.content())
                .map(dataBuffer -> {
                    var bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return bytes;
                });
    }
    public Mono<List<byte[]>> toByteArray(List<FilePart> fileParts) {
        return Flux.fromIterable(fileParts)
                .flatMap(this::toByteArray)
                .collectList();
    }
}
