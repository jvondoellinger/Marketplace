package zjg.marketplace.core.caching;

import reactor.core.publisher.Mono;

public interface ICacheProvider {
    /// Get the object on the memory cache
    <T> Mono<T> get(String key, Class<T> tClass);
    /// Put item in the cache
    Mono<Void> put(String key, Object obj);
    /// Remove a value with this key
    Mono<Void> evict(String key);
    /// Remove all values in the cache
    Mono<Void> clear();
}
