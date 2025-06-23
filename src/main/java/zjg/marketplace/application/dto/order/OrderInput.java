package zjg.marketplace.application.dto.order;

import java.util.List;

public class OrderInput {
     // Properties ------------------------------------------------------------
     private String userId;
     private List<String> productId;

     // Getter ----------------------------------------------------------------
     public String getUserId() {
          return userId;
     }
     public List<String> getProductId() {
          return productId;
     }

     // Setter ----------------------------------------------------------------
     public void setProductInput(List<String> productId) {
          this.productId = productId;
     }
     public void setUserId(String userId) {
          this.userId = userId;
     }
}
