package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntityAndInput;
import zjg.marketplace.application.service.promisse.IUpdateService;

import java.util.List;

@Service
public class UpdateResolver extends ResolverAbstract2 implements ResolveByEntityAndInput<IUpdateService<?, ?>> {

    @Autowired
    public UpdateResolver(List<IUpdateService<?, ?>> services) {
        super(services, IUpdateService.class);
    }

    @SuppressWarnings("unchecked")
    public <Entity, Input> IUpdateService<Entity, Input> resolve(Class<Entity> entityClass, Class<Input> inputClass) {
        return (IUpdateService<Entity, Input>) super.internalResolve(entityClass, inputClass);
    }

    @Deprecated
    protected Class<?> extractGenericType(IUpdateService<?, ?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(IUpdateService.class)
                .getGeneric(0)
                .resolve();
    }
}
