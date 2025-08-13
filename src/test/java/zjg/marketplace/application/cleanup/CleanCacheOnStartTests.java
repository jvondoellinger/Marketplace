package zjg.marketplace.application.cleanup;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import zjg.marketplace.MarketplaceApplication;
import zjg.marketplace.infrastructure.cache.config.RedisConfig;

import java.time.Duration;

@SpringBootTest(classes = {MarketplaceApplication.class, RedisConfig.class})
//@Order(1)
public class CleanCacheOnStartTests {


    @Autowired
    private ReactiveRedisTemplate<String, Object> template;

    public void cleanup() {
        if(template == null) System.out.println("adwad");
        template.getConnectionFactory()
                .getReactiveClusterConnection()
                .serverCommands()
                .flushDb()
                .doOnNext(x -> System.out.println("Successfully cleared cache!"))
                .doOnError(x -> System.out.println("Occurred an error while cleanup the cache on Redis!"))
                .block(Duration.ofSeconds(10));
    }

    @Test
    public void clean() {
        cleanup();
    }
}

