package zjg.marketplace.application.resolver.services.interfaces;

public interface ResolveByEntity<TService> {
    <Entity> TService resolve(Class<Entity> clazz);
}
