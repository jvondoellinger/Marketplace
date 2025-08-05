package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntity;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.application.service.promisse.IUploadService;

import java.util.List;

@Service
public class FindResolver extends ResolverAbstract2 implements ResolveByEntity<IFindService<?>> {

    @Autowired
    public FindResolver(List<IFindService<?>> services) {
        super(services, IFindService.class);
    }

    @SuppressWarnings("unchecked")
    public <T> IFindService<T> resolve(Class<T> clazz) {
        return (IFindService<T>) super.internalResolve(clazz);
    }

    @Deprecated
    protected Class<?> extractGenericType(IFindService<?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(IFindService.class)
                .getGeneric(0)
                .resolve();
    }
}
