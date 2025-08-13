package zjg.marketplace.application.resolver.facade;

import org.springframework.stereotype.Component;
import zjg.marketplace.application.resolver.services.impl.*;
import zjg.marketplace.application.service.promisse.*;

@Component
public class ServiceResolverFacade {
    private final CreateResolver createResolver;
    private final DeleteResolver deleteResolver;
    private final FindResolver findResolver;
    private final FindByUserIdResolver findByUserIdResolver;
    private final UpdateResolver updateResolver;
    private final UploadResolver uploadResolver;

    public ServiceResolverFacade(CreateResolver createResolver,
                                 DeleteResolver deleteResolver,
                                 FindResolver findResolver,
                                 FindByUserIdResolver findByUserIdResolver,
                                 UpdateResolver updateResolver,
                                 UploadResolver uploadResolver) {
        this.createResolver = createResolver;
        this.deleteResolver = deleteResolver;
        this.findResolver = findResolver;
        this.findByUserIdResolver = findByUserIdResolver;
        this.updateResolver = updateResolver;
        this.uploadResolver = uploadResolver;
    }
    public <Entity> IFindByUserId<Entity> resolveFindByUserId(Class<Entity> entityClass) {
        return findByUserIdResolver.resolve(entityClass);
    }
    public <Entity> FindService<Entity> resolveFind(Class<Entity> entityClass) {
        return findResolver.resolve(entityClass);
    }
    public <Entity> IDeleteService<Entity> resolveDelete(Class<Entity> entityClass) {
        return deleteResolver.resolve(entityClass);
    }
    public <Entity, Input> ICreateService<Entity, Input> resolveCreate(Class<Entity> entityClass, Class<Input> inputClass){
        return createResolver.resolve(entityClass, inputClass);
    }
    public <Entity, Input> IUpdateService<Entity, Input> resolveUpdate(Class<Entity> entityClass, Class<Input> inputClass){
        return updateResolver.resolve(entityClass, inputClass);
    }
    public <Entity> IUploadService<Entity> resolveUpload(Class<Entity> entityClass){
        return uploadResolver.resolve(entityClass);
    }
}
