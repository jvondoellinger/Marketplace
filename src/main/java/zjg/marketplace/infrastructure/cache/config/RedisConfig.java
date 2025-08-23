package zjg.marketplace.infrastructure.cache.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import zjg.marketplace.core.product.entity.Product;

import java.time.Duration;
import java.util.Date;

@Configuration
public class RedisConfig {
    private static final Integer EXPIRE_MINUTES = 5;

    @Bean(name = "redisObjectMapper")
    public ObjectMapper redisObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("zjg.marketplace.core") 
                .allowIfSubType(Date.class)
                .allowIfBaseType(Object.class)
                .build();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, true);
        return mapper;
    }

    @Bean
    public ReactiveRedisTemplate<String, Product> generateReactiveTemplate2(ReactiveRedisConnectionFactory factory, @Qualifier("redisObjectMapper") ObjectMapper redisObjectMapper) {
        var redisSeria = new StringRedisSerializer();
        var jacksonSerializer = new Jackson2JsonRedisSerializer<>(Product.class);
        jacksonSerializer.setObjectMapper(redisObjectMapper);
        var context = RedisSerializationContext
                .<String, Product>newSerializationContext(redisSeria)
                .value(jacksonSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> generateReactiveTemplate(ReactiveRedisConnectionFactory factory, @Qualifier("redisObjectMapper") ObjectMapper redisObjectMapper) {
        var redisSeria = new StringRedisSerializer();
        var jacksonSerializer = new GenericJackson2JsonRedisSerializer(redisObjectMapper);
        var context = RedisSerializationContext
                .<String, Object>newSerializationContext(redisSeria)
                .value(jacksonSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(@Qualifier("redisObjectMapper") ObjectMapper redisObjectMapper) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(redisObjectMapper)))
                .entryTtl(Duration.ofMinutes(EXPIRE_MINUTES));
    }



}
