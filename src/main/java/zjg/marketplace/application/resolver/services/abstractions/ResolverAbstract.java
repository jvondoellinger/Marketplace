package zjg.marketplace.application.resolver.services.abstractions;

import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.core.ResolvableType;
import reactor.util.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class ResolverAbstract<TService> {
    private final Map<Class<?>, TService> serviceMap = new HashMap<>(); // Depois colocar oTService primeiro!
    private final static String IMPLEMENTATION_NOT_FOUND = "This service doesn't have an implementation for this type!";
    private final static String GENERIC_TYPE_NOT_EQUALS = "This service does not have an implementation with these generic types!";
    private static final Logger logger = LoggerFactory.getLogger(ResolverAbstract.class);
    private final Class<?> serviceClass;
    /// * With this base, you can resolve the implementation of your interface, only need a typa
    /// * Limitations: Key is unique - contention to no subscribe another service!
    /// * Can have 1 generic type by instance -> TService<>
    //- * To understand more, see application logs!
    protected ResolverAbstract(@NonNull @NotEmpty List<TService> services, Class<?> serviceClass) {
        this.serviceClass = serviceClass;
        services.parallelStream().forEach(service -> {
            var type = validateAndPut(service);
            if(Objects.isNull(type)) return;
            serviceMap.put(type, service);
        });
    }
    // Methods
    @SuppressWarnings("unchecked")
    protected <T> TService internalResolve(@NonNull Class<T> genericClass) {
        var service = serviceMap.get(genericClass);
        if(Objects.isNull(service)) throw new IllegalArgumentException(IMPLEMENTATION_NOT_FOUND);
        var target = AopProxyUtils.ultimateTargetClass(service); // Because the @Cacheable generate a proxy in the class!
        if(!validType(genericClass, target, 0)) throw new IllegalArgumentException(GENERIC_TYPE_NOT_EQUALS);
        return service;
    }
    @SuppressWarnings("unchecked")
    protected <T, Y> TService internalResolve(Class<T> genericClass1, Class<Y> genericClass2) {
        var service = internalResolve(genericClass1);
        if(!validType(genericClass2, service.getClass(), 1)) throw new IllegalArgumentException(GENERIC_TYPE_NOT_EQUALS);
        return service;
    }

    // Abstract to be implemented and return the exact type
    protected abstract Class<?> extractGenericType(TService service);
    protected Class<?> test(TService s) {
        return ResolvableType.forInstance(s)
                .as(serviceClass)
                .getGeneric(0)
                .resolve();
    }
    // Auxiliary method - Helpers
    protected final ResolvableType generateResolvableType(TService service) {
        return ResolvableType.forClass(service.getClass());
    }

    // Custom methods
    private <T, TypeService> boolean validType(Class<T> genericTypeClass, Class<TypeService> implClass, int index) {
        if (genericTypeClass == null || implClass == null || index < 0) {
            return false;
        }
        for(var iface : implClass.getGenericInterfaces()) {
            if(iface instanceof ParameterizedType pt) {
                var rawType = pt.getRawType();
                var args = pt.getActualTypeArguments();
                if(index < args.length) return args[index].equals(genericTypeClass);
            }
        }
        return false;
    }

    private Class<?> validateAndPut(TService service) {
        var type = extractGenericType(service);
        var formated = String.format("Type: %s - Service: %s", type, service);
        if (Objects.isNull(service)){
            logger.error("ERROR TO RESOLVE SERVICE: {}", formated);
        } else if(Objects.isNull(type)) {
            logger.warn("WARNING: {}", formated);
        } else {
            logger.info(formated);
        }
        return type;
    }
}
