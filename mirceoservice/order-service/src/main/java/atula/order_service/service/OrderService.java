package atula.order_service.service;

import atula.order_service.dto.OrderLineItemsRequest;
import atula.order_service.dto.OrderRequest;
import atula.order_service.model.PlaceOrder;
import atula.order_service.model.OrderLineItems;
import atula.order_service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){

        PlaceOrder o = new PlaceOrder();
        o.setOrderNumber(UUID.randomUUID().toString());


       o.setOrderLineItemsList( orderRequest.getOrderLineItemsRequests().stream().map(orderLineItemsRequest -> mapToOrderLineItem(orderLineItemsRequest)).toList());

        orderRepository.save(o);
    }
    private OrderLineItems mapToOrderLineItem(OrderLineItemsRequest orderLineItemsRequest){
        OrderLineItems orderLineItems = OrderLineItems.builder()
                .skuCode(orderLineItemsRequest.getSkuCode())
                .quantity(orderLineItemsRequest.getQuantity())
                .price(orderLineItemsRequest.getPrice())
                .build();

        return orderLineItems;
    }


}
