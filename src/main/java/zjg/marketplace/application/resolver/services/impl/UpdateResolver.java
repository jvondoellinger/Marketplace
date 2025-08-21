package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntityAndInput;
import zjg.marketplace.application.service.promisse.UpdateService;

import java.util.List;

@Service
public class UpdateResolver extends ResolverAbstract2 implements ResolveByEntityAndInput<UpdateService<?, ?>> {

    @Autowired
    public UpdateResolver(List<UpdateService<?, ?>> services) {
        super(services, UpdateService.class);
    }

    @SuppressWarnings("unchecked")
    public <Entity, Input> UpdateService<Entity, Input> resolve(Class<Entity> entityClass, Class<Input> inputClass) {
        return (UpdateService<Entity, Input>) super.internalResolve(entityClass, inputClass);
    }

    @Deprecated
    protected Class<?> extractGenericType(UpdateService<?, ?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(UpdateService.class)
                .getGeneric(0)
                .resolve();
    }
}
