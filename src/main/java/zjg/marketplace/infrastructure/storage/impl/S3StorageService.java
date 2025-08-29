package zjg.marketplace.infrastructure.storage.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.BytesWrapper;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import zjg.marketplace.core.image.entity.BasicImageFile;
import zjg.marketplace.core.logging.services.Logger;
import zjg.marketplace.core.image.valueObj.path.ImagePath;
import zjg.marketplace.core.image.service.StorageService;
import zjg.marketplace.infrastructure.storage.config.S3Config;
import zjg.marketplace.infrastructure.storage.utils.StorageMessageUtils;

import java.util.Objects;

@Service
public class S3StorageService implements StorageService {
    private final S3AsyncClient s3AsyncClient;
    private final S3Config config;
    private final Logger logger;
    private static final Class<S3StorageService> self = S3StorageService.class;
    public S3StorageService(S3AsyncClient s3AsyncClient, S3Config config, Logger logger) {
        this.s3AsyncClient = s3AsyncClient;
        this.config = config;
        this.logger = logger;
    }
    
    @Override
    public Mono<BasicImageFile> upload(BasicImageFile file) {
        if(Objects.isNull(file)) {
            return Mono.error(new RuntimeException("File cannot be null"));
        }
        System.out.println("Bucket -> : "+config.getBucket());

        var completePath = file.getPath().getCompletePath();
        var putRequest = PutObjectRequest.builder()
                .bucket(config.getBucket())
                .key(completePath)
                .build();
        var futurePutResponse = s3AsyncClient
                .putObject(putRequest,
                        AsyncRequestBody.fromBytes(file.getBinary()));
        logger.info(self, StorageMessageUtils.infoUploadingFile(completePath));
        return Mono.fromFuture(futurePutResponse)
                .thenReturn(file)
                .doOnNext(entity -> logger.info(self, StorageMessageUtils.successUploadingFile(completePath)))
                .doOnError(ex -> logger.error(self, StorageMessageUtils.errorUploadingFile(completePath, ex.getMessage()), ex));
    }

    @Override
    public Mono<Void> remove(ImagePath path) {
        var completePath = path.getCompletePath();
        var deleteRequest = DeleteObjectRequest.builder()
                .bucket(config.getBucket())
                .key(completePath)
                .build();
        logger.info(self, StorageMessageUtils.infoRemovingFile(completePath));
        return Mono.fromFuture(s3AsyncClient.deleteObject(deleteRequest))
                .then()
                .doOnNext(x -> logger.info(self, StorageMessageUtils.successRemovingFile(completePath)))
                .doOnError(ex -> logger.error(self, StorageMessageUtils.errorRemovingFile(completePath, ex.getMessage()), ex));
    }

    @Override
    public Mono<byte[]> get(ImagePath path) {
        var completePath = path.getCompletePath();
        var getRequest = GetObjectRequest.builder()
                .bucket(config.getBucket())
                .key(completePath)
                .build();
        logger.info(self, StorageMessageUtils.infoSearchingFile(completePath));
        return Mono.fromFuture(s3AsyncClient.getObject(getRequest, AsyncResponseTransformer.toBytes()))
                .map(BytesWrapper::asByteArray)
                .doOnNext(b -> logger.error(self, StorageMessageUtils.successUploadingFile(completePath)))
                .doOnError(ex -> logger.error(self, StorageMessageUtils.errorSearchingFile(completePath, ex.getMessage()), ex));
    }
}
