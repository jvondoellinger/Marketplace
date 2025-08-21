package zjg.marketplace.presentation.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.product.strategies.updater.exceptions.ProductUpdaterException;
import zjg.marketplace.core.user.strategies.updater.exceptions.UserUpdaterException;
import zjg.marketplace.core.order.strategies.valdiators.exceptions.OrderException;
import zjg.marketplace.core.order.repository.exceptions.OrderNotFoundException;
import zjg.marketplace.core.product.repository.exceptions.ProductNotFoundException;
import zjg.marketplace.core.product.strategies.validators.exceptions.description.DescriptionException;
import zjg.marketplace.core.order.strategies.valdiators.exceptions.path.PathException;
import zjg.marketplace.core.product.strategies.validators.exceptions.title.TitleException;
import zjg.marketplace.core.security.exceptions.InvalidTokenException;
import zjg.marketplace.core.user.repository.exceptions.UserNotFoundException;
import zjg.marketplace.core.user.strategies.validators.exceptions.birthday.BirthdayException;
import zjg.marketplace.core.user.strategies.validators.exceptions.cpf.CpfException;
import zjg.marketplace.core.user.strategies.validators.exceptions.email.EmailException;
import zjg.marketplace.core.user.strategies.validators.exceptions.password.PasswordException;
import zjg.marketplace.core.user.strategies.validators.exceptions.phone.PhoneException;
import zjg.marketplace.core.user.strategies.validators.exceptions.username.UsernameException;
import zjg.marketplace.presentation.model.ErrorResponse;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Order(-2)
public class GlobalErrorHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper mapper;
    private List<Class<? extends RuntimeException>> badRequest;
    public GlobalErrorHandler(ObjectMapper mapper) {
        this.mapper = mapper;
        initList();
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (exchange.getResponse().isCommitted()) return Mono.error(ex);

        HttpStatus status = getByException(ex);
        var error = new ErrorResponse(ex.getMessage());
        byte[] bytes;
        RuntimeException exs = null;
        try {
            exs = new RuntimeException();
            bytes = mapper.writeValueAsBytes(error);
        } catch (JsonProcessingException e) {
            bytes = "Internal error, please notify the TI department!".getBytes(StandardCharsets.UTF_8);
            exs = new RuntimeException(e);
        }

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        if(buffer == null)
            return exchange.getResponse().writeWith(Mono.just(buffer));
        else throw new RuntimeException(ex);
    }

    private HttpStatus getByException(Throwable ex) {
        if (badRequest.stream().anyMatch(clazz -> clazz.isInstance(ex))) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    private void initList() {
        badRequest = List.of(OrderException.class,
                DescriptionException.class,
                PathException.class,
                TitleException.class,
                BirthdayException.class,
                CpfException.class,
                EmailException.class,
                PasswordException.class,
                PhoneException.class,
                UsernameException.class,
                ProductUpdaterException.class,
                UserUpdaterException.class,
                UserNotFoundException.class,
                ProductNotFoundException.class,
                OrderNotFoundException.class,
                InvalidTokenException.class);
    }
}
