package zjg.marketplace.application.resolver.services.interfaces;

public interface ResolveByEntityAndInput<TService> {
    <Entity, Input> TService resolve(Class<Entity> entityClass, Class<Input> inputClass);
}
