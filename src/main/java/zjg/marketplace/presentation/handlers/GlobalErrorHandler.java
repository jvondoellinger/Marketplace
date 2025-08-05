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
import zjg.marketplace.core.exceptions.updater.product.ProductUpdaterException;
import zjg.marketplace.core.exceptions.updater.user.UserUpdaterException;
import zjg.marketplace.core.exceptions.validations.order.OrderException;
import zjg.marketplace.core.exceptions.validations.order.OrderNotFoundException;
import zjg.marketplace.core.exceptions.validations.product.ProductNotFoundException;
import zjg.marketplace.core.exceptions.validations.product.description.DescriptionException;
import zjg.marketplace.core.exceptions.validations.product.path.PathException;
import zjg.marketplace.core.exceptions.validations.product.title.TitleException;
import zjg.marketplace.core.exceptions.validations.token.InvalidTokenException;
import zjg.marketplace.core.exceptions.validations.user.UserNotFoundException;
import zjg.marketplace.core.exceptions.validations.birthday.BirthdayException;
import zjg.marketplace.core.exceptions.validations.cpf.CpfException;
import zjg.marketplace.core.exceptions.validations.email.EmailException;
import zjg.marketplace.core.exceptions.validations.password.PasswordException;
import zjg.marketplace.core.exceptions.validations.phone.PhoneException;
import zjg.marketplace.core.exceptions.validations.username.UsernameException;
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

        try {
            bytes = mapper.writeValueAsBytes(error);
        } catch (JsonProcessingException e) {
            bytes = "Internal error, please notify the TI department!".getBytes(StandardCharsets.UTF_8);
        }

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
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
