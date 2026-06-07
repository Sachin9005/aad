package lk.ijse.aad.controller;

import lk.ijse.aad.dto.SaveOrderDTO;
import lk.ijse.aad.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveOrder(@RequestBody SaveOrderDTO orderDTO) {
        orderService.saveOrder(orderDTO);
        return "Order saved successfully";
    }
}
