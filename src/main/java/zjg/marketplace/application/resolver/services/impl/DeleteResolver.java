package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntity;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract;
import zjg.marketplace.application.service.promisse.IDeleteService;

import java.util.List;

@Service
public class DeleteResolver extends ResolverAbstract2 implements ResolveByEntity<IDeleteService<?>> {
    @Autowired
    public DeleteResolver(List<IDeleteService<?>> services) {
        super(services, IDeleteService.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Entity> IDeleteService<Entity> resolve(Class<Entity> entityClass) {
        return (IDeleteService<Entity>) super.internalResolve(entityClass);
    }

    @Deprecated
    protected Class<?> extractGenericType(IDeleteService<?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(IDeleteService.class)
                .getGeneric(0)
                .resolve();
    }
}
