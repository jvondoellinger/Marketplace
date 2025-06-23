package zjg.marketplace.infrastructure.storage.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.BytesWrapper;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.field.path.CustomPath;
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

    // Problemas com a extensão da imagem!

    @BadCode
    @Override
    public Mono<BasicImageFile> upload(BasicImageFile file) {
        if(Objects.isNull(file)) {
            return Mono.error(new RuntimeException("File cannot be null"));
        }
        var putRequest = PutObjectRequest.builder()
                .bucket(config.getBucket())
                .key(file.getPath().getPath())
                .build();
        var fix = PathUtils.removeBars(file.getPath().getPath());
        var futurePutResponse = s3AsyncClient
                .putObject(putRequest,
                        AsyncRequestBody.fromBytes(file.getBinary()));
        return Mono.fromFuture(futurePutResponse).thenReturn(file);
    }

    @Override
    public Mono<Void> remove(CustomPath path) {
        return null;
    }

    @Override
    public Mono<byte[]> get(CustomPath path) {
        var getRequest = GetObjectRequest.builder()
                .bucket(config.getBucket())
                .key(path.getPath())
                .build();
        return Mono.fromFuture(s3AsyncClient.getObject(getRequest, AsyncResponseTransformer.toBytes()))
                .map(BytesWrapper::asByteArray)
                .onErrorMap(t -> new RuntimeException("Sorry, but it's not possible to get the file!"));
    }
}
