package zjg.marketplace.application.service.helper.valueObj;

import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.user.entity.User;

import java.util.List;

public class UserProductPair {
    public UserProductPair() {

    }

    public UserProductPair(User key, List<Product> value) {
        this.key = key;
        this.value = value;
    }

    private User key;
    private List<Product> value;

    // * Getter
    public User getKey() {
        return key;
    }
    public List<Product> getValue() {
        return value;
    }

    // * Setter
    public void setKey(User key) {
        this.key = key;
    }
    public void setValue(List<Product> value) {
        this.value = value;
    }
}
