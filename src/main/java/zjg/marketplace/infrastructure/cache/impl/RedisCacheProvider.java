package zjg.marketplace.infrastructure.cache.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.caching.ICacheProvider;

@Service
public class RedisCacheProvider implements ICacheProvider {
    private final ReactiveRedisTemplate<String, Object> template;
    private final ObjectMapper mapper;

    public RedisCacheProvider(ReactiveRedisTemplate<String, Object> template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public <T> Mono<T> get(String key, Class<T> tClass) {
        return template.opsForValue().get(key)
                .map(x -> mapper.convertValue(x, tClass));
    }

    @Override
    public Mono<Void> put(String key, Object obj) {
        return template.opsForValue().set(key, obj).then();
    }

    @Override
    public Mono<Void> evict(String key) {
        return template.opsForValue().delete(key).then();
    }
    @BadCode // Do this in batch, to gain performance
    @Override
    public Mono<Void> clear() {
        return template.keys("*")
                .flatMap(template::delete)
                .then();
    }
}
