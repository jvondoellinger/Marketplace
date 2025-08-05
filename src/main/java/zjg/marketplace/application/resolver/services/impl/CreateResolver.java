package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntityAndInput;
import zjg.marketplace.application.service.promisse.ICreateService;

import java.util.List;

@Service
public class CreateResolver extends ResolverAbstract2 implements ResolveByEntityAndInput<ICreateService<?, ?>> {
    @Autowired
    public CreateResolver(List<ICreateService<?,?>> services) {
        super(services, ICreateService.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Entity, Input> ICreateService<Entity, Input> resolve(Class<Entity> entityClass, Class<Input> inputClass) {
        return (ICreateService<Entity, Input>) super.internalResolve(entityClass, inputClass);
    }

    @Deprecated
    protected Class<?> extractGenericType(ICreateService<?, ?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(ICreateService.class)
                .getGeneric(0)
                .resolve();
    }
}
