package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntity;
import zjg.marketplace.application.service.promisse.FindService;

import java.util.List;

@Service
public class FindResolver extends ResolverAbstract2 implements ResolveByEntity<FindService<?>> {

    @Autowired
    public FindResolver(List<FindService<?>> services) {
        super(services, FindService.class);
    }

    @SuppressWarnings("unchecked")
    public <T> FindService<T> resolve(Class<T> clazz) {
        return (FindService<T>) super.internalResolve(clazz);
    }

    @Deprecated
    protected Class<?> extractGenericType(FindService<?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(FindService.class)
                .getGeneric(0)
                .resolve();
    }
}
