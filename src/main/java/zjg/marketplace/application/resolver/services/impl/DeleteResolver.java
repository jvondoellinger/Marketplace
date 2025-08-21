package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntity;
import zjg.marketplace.application.service.promisse.DeleteService;

import java.util.List;

@Service
public class DeleteResolver extends ResolverAbstract2 implements ResolveByEntity<DeleteService<?>> {
    @Autowired
    public DeleteResolver(List<DeleteService<?>> services) {
        super(services, DeleteService.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Entity> DeleteService<Entity> resolve(Class<Entity> entityClass) {
        return (DeleteService<Entity>) super.internalResolve(entityClass);
    }

    @Deprecated
    protected Class<?> extractGenericType(DeleteService<?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(DeleteService.class)
                .getGeneric(0)
                .resolve();
    }
}
