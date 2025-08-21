package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntityAndInput;
import zjg.marketplace.application.service.promisse.CreateService;

import java.util.List;

@Service
public class CreateResolver extends ResolverAbstract2 implements ResolveByEntityAndInput<CreateService<?, ?>> {
    @Autowired
    public CreateResolver(List<CreateService<?,?>> services) {
        super(services, CreateService.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Entity, Input> CreateService<Entity, Input> resolve(Class<Entity> entityClass, Class<Input> inputClass) {
        return (CreateService<Entity, Input>) super.internalResolve(entityClass, inputClass);
    }

    @Deprecated
    protected Class<?> extractGenericType(CreateService<?, ?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(CreateService.class)
                .getGeneric(0)
                .resolve();
    }
}
