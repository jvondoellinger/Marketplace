package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.resolver.services.interfaces.ResolveByEntity;
import zjg.marketplace.application.service.promisse.IUploadService;

import java.util.List;

@Service
public class UploadResolver extends ResolverAbstract2 implements ResolveByEntity<IUploadService<?>> {
    @Autowired
    public UploadResolver(List<IUploadService<?>> services){
        super(services, IUploadService.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Entity> IUploadService<Entity> resolve(Class<Entity> clazz) {
        return (IUploadService<Entity>) super.internalResolve(clazz);
    }

    @Deprecated
    protected Class<?> extractGenericType(IUploadService<?> service) {
        return ResolvableType.forClass(service.getClass())
                .as(IUploadService.class)
                .getGeneric(0)
                .resolve();
    }
}
