package com.example.OrderProcessingApplication.controller;

import com.example.OrderProcessingApplication.dto.OrderRequest;
import com.example.OrderProcessingApplication.entity.Order;
import com.example.OrderProcessingApplication.enums.OrderEvent;
import com.example.OrderProcessingApplication.enums.OrderState;
import com.example.OrderProcessingApplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request.getDescription());
    }

    @PostMapping("/{id}/event/{event}")
    public Order sendEvent(@PathVariable Long id, @PathVariable OrderEvent event) {
        return orderService.processEvent(id, event);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}/state")
    public OrderState getState(@PathVariable Long id) {
        return orderService.getCurrentState(id);
    }

    @GetMapping("/{id}/allowed-events")
    public List<OrderEvent> getAllowedEvents(@PathVariable Long id) {
        return orderService.getAllowedEvents(id);
    }

    @GetMapping("/{id}/history")
    public List<String> getOrderHistory(@PathVariable Long id) {
        return orderService.getStateHistory(id);
    }
}
