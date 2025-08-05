package zjg.marketplace.application.resolver.services.abstractions;

import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import reactor.util.annotation.NonNull;
import zjg.marketplace.application.logger.BasicLogger;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class ResolverAbstract2 {
    private final static String IMPLEMENTATION_NOT_FOUND = "This service doesn't have an implementation for this type!";
    private final static String GENERIC_TYPE_NOT_EQUALS = "This service does not have an implementation with these generic types!";

    private final Map<Class<?>, Object> serviceMap = new HashMap<>(); // Depois colocar oTService primeiro!
    private final Class<?> serviceClass;
    /// * With this base, you can resolve the implementation of your interface, only need a typa
    /// * Limitations: Key is unique - contention to no subscribe another service!
    /// * Can have 1 generic type by instance -> TService<>
    //- * To understand more, see application logs!
    @Autowired
    protected ResolverAbstract2(@NonNull @NotEmpty List<?> services, Class<?> serviceClass) {
        this.serviceClass = serviceClass;
        services.parallelStream().forEach(service -> {
            var type = validateAndPut(service);
            if(Objects.isNull(type)) return;
            BasicLogger.warning(this.getClass(), "RESOLVED -> Adding type {} to be list when can be resolved!", type);
            serviceMap.put(type, service);
        });
    }
    // Methods
    @SuppressWarnings("unchecked")
    protected <T> Object internalResolve(@NonNull Class<T> genericClass) {
        var service = serviceMap.get(genericClass);
        if(Objects.isNull(service)) {
            BasicLogger.error(this.getClass(), "RESOLVE: -> CANNOT RESOLVE TYPE: {}", genericClass);
            throw new IllegalArgumentException(IMPLEMENTATION_NOT_FOUND);
        }
        var target = AopProxyUtils.ultimateTargetClass(service);
        if(!validTypeWithProxy(service, genericClass, 0)) throw new IllegalArgumentException(GENERIC_TYPE_NOT_EQUALS);
        return service;
    }
    @SuppressWarnings("unchecked")
    protected <T, Y> Object internalResolve(Class<T> genericClass1, Class<Y> genericClass2) {
        var service = internalResolve(genericClass1);
        if(!validTypeWithProxy(service, genericClass1, 0)) throw new IllegalArgumentException(GENERIC_TYPE_NOT_EQUALS);
        if(!validTypeWithProxy(service, genericClass2, 1)) throw new IllegalArgumentException(GENERIC_TYPE_NOT_EQUALS);
        return service;
    }

    // Abstract to be implemented and return the exact type
    //protected abstract Class<?> extractGenericType(TService service);
    @SuppressWarnings("unchecked")
    private <T> Class<T> extractGenericType(T s) {
        return (Class<T>) ResolvableType.forClass(s.getClass())
                .as(serviceClass)
                .getGeneric(0)
                .resolve();
    }
    // Auxiliary method - Helpers

    // Custom methods
    private <T, TypeService> boolean validType(Class<T> genericTypeClass, Class<TypeService> implClass, int index) {
        if (genericTypeClass == null || implClass == null || index < 0) {
            return false;
        }
        for(var iface : implClass.getGenericInterfaces()) {
            if(iface instanceof ParameterizedType pt) {
                var args = pt.getActualTypeArguments();
                if(!(index < args.length)) return false;
                var actualType = args[index];
                if(actualType instanceof Class<?> actualClass) {
                    return actualClass.equals(genericTypeClass);
                }
            }
        }
        BasicLogger.error(this.getClass(),"RESOLVE: -> ERROR: {} - {}", genericTypeClass.toGenericString(), implClass.toGenericString());
        return false;
    }
    private <T> Class<T> validateAndPut(T service) {
        var type = extractGenericType(service);
        var formated = String.format("Resolver: Type: %s - Service: %s", type, service);
        if (Objects.isNull(service)) BasicLogger.error(this.getClass(), "ERROR TO RESOLVE SERVICE: {}", formated);
        if(Objects.isNull(type)) BasicLogger.warning(this.getClass(), "WARNING: {}", formated);
        else BasicLogger.info(this.getClass(), formated);

        return type;
    }

    private <T, TypeService> boolean validTypeWithProxy(Object service, Class<T> genericTypeClass, int index) {
        Class<?> clazz = null;
        if(AopUtils.isAopProxy(service)){
            clazz = AopProxyUtils.ultimateTargetClass(service);
        } else {
            clazz = service.getClass();
        }
        return validType(genericTypeClass, clazz , index);
    }
}
