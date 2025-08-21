package zjg.marketplace.infrastructure.cache.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.user.entity.User;

import java.time.Duration;

@Configuration
public class RedisConfig {
    private static final Integer EXPIRE_MINUTES = 5;

/*    @Bean
    public ReactiveRedisTemplate<String, Object> generateReactiveTemplate(ReactiveRedisConnectionFactory factory, ObjectMapper mapper) {
        *//*mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );*//*
        var redisSeria = new StringRedisSerializer();
        var jacksonSerializer = new GenericJackson2JsonRedisSerializer(mapper);
        var context = RedisSerializationContext
                .<String, Object>newSerializationContext(redisSeria)
                .value(jacksonSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }*/
    @Bean
    public ReactiveRedisTemplate<String, User> generateUserReactiveTemplate(ReactiveRedisConnectionFactory factory) {
        var mapper = new ObjectMapper();
        mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        var redisSeria = new StringRedisSerializer();
        var jacksonSerializer = new Jackson2JsonRedisSerializer<>(User.class);
        jacksonSerializer.setObjectMapper(mapper);
        var context = RedisSerializationContext
                .<String, User>newSerializationContext(redisSeria)
                .value(jacksonSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
    @Bean
    public ReactiveRedisTemplate<String, Product> generateProductReactiveTemplate(ReactiveRedisConnectionFactory factory) {
        var mapper = new ObjectMapper();
        mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        var redisSeria = new StringRedisSerializer();
        var jacksonSerializer = new Jackson2JsonRedisSerializer<>(Product.class);
        jacksonSerializer.setObjectMapper(mapper);
        var context = RedisSerializationContext
                .<String, Product>newSerializationContext(redisSeria)
                .value(jacksonSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
    @Bean
    public ReactiveRedisTemplate<String, Order> generateOrderReactiveTemplate(ReactiveRedisConnectionFactory factory) {
        var mapper = new ObjectMapper();
        mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        var redisSeria = new StringRedisSerializer();
        var jacksonSerializer = new Jackson2JsonRedisSerializer<>(Order.class);
        jacksonSerializer.setObjectMapper(mapper);
        var context = RedisSerializationContext
                .<String, Order>newSerializationContext(redisSeria)
                .value(jacksonSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(ObjectMapper mapper) {

        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(mapper)))
                .entryTtl(Duration.ofMinutes(EXPIRE_MINUTES));
    }
}
