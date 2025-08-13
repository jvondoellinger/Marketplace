
package zjg.marketplace.infrastructure.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import zjg.marketplace.infrastructure.cache.config.RedisConfig;

import java.time.Duration;

@SpringBootTest
@Import(RedisConfig.class)
@Order(1)
class RedisCacheCleanupTest {
    @Autowired
    private ReactiveRedisTemplate<String, Object> template;

    public void cleanup() {
        template.getConnectionFactory()
                .getReactiveClusterConnection()
                .serverCommands()
                .flushDb()
                .doOnNext(x -> System.out.println("Successfully cleared cache!"))
                .doOnError(x -> System.out.println("Occurred an error while cleanup the cache on Redis!"))
                .block(Duration.ofSeconds(10));
    }
}
