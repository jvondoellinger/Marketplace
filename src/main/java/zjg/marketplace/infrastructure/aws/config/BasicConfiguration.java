package zjg.marketplace.infrastructure.aws.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties("aws")
public class BasicConfiguration {
    private String endpoint;
    private String region;
    private String accessKey;
    private String secretKey;

    // * Getter
    public String getSecretKey() {
        return secretKey;
    }
    public String getAccessKey() {
        return accessKey;
    }
    public String getRegion() {
        return region;
    }
    public String getEndpoint() {
        return endpoint;
    }

    // * Setter
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
