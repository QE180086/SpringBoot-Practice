package atula.order_service.controller;

import atula.order_service.dto.OrderLineItemsRequest;
import atula.order_service.dto.OrderRequest;
import atula.order_service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")

public class OrderController {

    @Autowired
    private OrderService orderService;
@PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest){
    orderService.placeOrder(orderRequest);
    return "Place Order successfull";
}


}
