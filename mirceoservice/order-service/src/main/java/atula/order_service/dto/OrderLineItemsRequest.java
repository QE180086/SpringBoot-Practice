package atula.order_service.dto;

import atula.order_service.model.OrderLineItems;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsRequest {
    private String skuCode;
    private BigDecimal price;
    private int quantity;


}
