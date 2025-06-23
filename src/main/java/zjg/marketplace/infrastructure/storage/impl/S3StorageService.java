package zjg.marketplace.infrastructure.storage.impl;

import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.BytesWrapper;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import zjg.marketplace.anotation.BadCode;
import zjg.marketplace.core.interfaces.storage.StorageService;
import zjg.marketplace.core.utils.PathUtils;
import zjg.marketplace.infrastructure.storage.config.S3Config;

import java.util.Objects;

@Service
public class S3StorageService implements StorageService {
    private final S3AsyncClient s3AsyncClient;
    private final S3Config config;
    public S3StorageService(S3AsyncClient s3AsyncClient, S3Config config) {
        this.s3AsyncClient = s3AsyncClient;
        this.config = config;
    }
    @BadCode

    @Override
    public Mono<String> upload(FilePart file, String fileName) {
        if(Objects.isNull(file)) {
            return Mono.error(new RuntimeException("File cannot be null"));
        }
        var fix = PathUtils.removeBars(fileName);
        var putRequest = PutObjectRequest.builder()
                .bucket(config.getBucket())
                .key(fix)
                .contentType(file.headers().getContentType().toString())
                .build();
        return file.content()
                .reduce((buffer1, buffer2) -> {
                    buffer1.write(buffer2);
                    return buffer1;
                })
                .flatMap(buffer -> {
                    byte[] bytes = new byte[buffer.readableByteCount()];
                    buffer.read(bytes);
                    return Mono.fromFuture(s3AsyncClient.putObject(putRequest, AsyncRequestBody.fromBytes(bytes)));
                }).then(Mono.just(fileName));
    }

    @Override
    public Mono<Void> remove(Resource file) {
        return null;
    }

    @Override
    public Mono<byte[]> get(String name) {
        var getRequest = GetObjectRequest.builder()
                .bucket(config.getBucket())
                .key(PathUtils.removeBars(name))
                .build();
        return Mono.fromFuture(s3AsyncClient.getObject(getRequest, AsyncResponseTransformer.toBytes()))
                .map(BytesWrapper::asByteArray);
    }
}
