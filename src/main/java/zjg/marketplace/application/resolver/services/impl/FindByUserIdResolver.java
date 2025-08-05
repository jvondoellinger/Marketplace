package zjg.marketplace.application.resolver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.resolver.services.abstractions.ResolverAbstract2;
import zjg.marketplace.application.service.promisse.IFindByUserId;

import java.util.List;

@Service
public class FindByUserIdResolver extends ResolverAbstract2 {
    @Autowired
    public FindByUserIdResolver(List<IFindByUserId<?>> services) {
        super(services, IFindByUserId.class);
    }

    @SuppressWarnings("unchecked")
    public <TClass> IFindByUserId<TClass> resolve(Class<TClass> clazz) {
        return (IFindByUserId<TClass>) internalResolve(clazz);
    }
}
