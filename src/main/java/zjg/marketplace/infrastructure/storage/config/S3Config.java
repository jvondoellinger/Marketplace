package zjg.marketplace.infrastructure.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Configuration;
import zjg.marketplace.infrastructure.aws.config.BasicConfiguration;

import java.net.URI;

@Configuration
@ConfigurationProperties("aws.s3")
public class S3Config {
    private final BasicConfiguration configuration;
    private String bucket;
    public S3Config(BasicConfiguration configuration) {
        this.configuration = configuration;
    }

    @Bean
    public S3AsyncClient s3AsyncClient() {
        var credentials = AwsBasicCredentials.create(configuration.getAccessKey(), configuration.getSecretKey());
        return S3AsyncClient.builder()
                .endpointOverride(URI.create(configuration.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.of(configuration.getRegion()))
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .httpClientBuilder(NettyNioAsyncHttpClient.builder())
                .build();
    }

    // Getter
    public String getBucket() {
        return bucket;
    }
    // Setter
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
