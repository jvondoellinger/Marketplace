package zjg.marketplace.infrastructure.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
    private static final Integer EXPIRE_MINUTES = 5;
    @Bean
    public ReactiveRedisTemplate<String, Object> generateReactiveTemplate(ReactiveRedisConnectionFactory factory) {
        var redisSeria = new StringRedisSerializer();
        var jacksonSerializer = new GenericJackson2JsonRedisSerializer();
        var context = RedisSerializationContext
                .<String, Object>newSerializationContext(redisSeria)
                .value(jacksonSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofMinutes(EXPIRE_MINUTES));
    }
}
