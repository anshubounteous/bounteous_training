package com.example.OrderProcessingApplication.service;


import com.example.OrderProcessingApplication.entity.Order;
import com.example.OrderProcessingApplication.enums.OrderEvent;
import com.example.OrderProcessingApplication.enums.OrderState;
import com.example.OrderProcessingApplication.exception.InvalidStateException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final Map<Long, Order> orders = new HashMap<>();
    private long idCounter = 1;

    public Order createOrder(String description) {
        Order order = new Order(idCounter++, description);
        orders.put(order.getId(), order);
        return order;
    }

    public Order getOrder(Long id) {
        return orders.get(id);
    }

    public Order processEvent(Long id, OrderEvent event) {
        Order order = orders.get(id);
        if (order == null) throw new RuntimeException("Order not found");

        OrderState currentState = order.getState();
        switch (event) {
            case PROCESS:
                if (currentState == OrderState.NEW)
                    order.setState(OrderState.PROCESSING);
                else throw invalid(currentState, event);
                break;
            case SHIP:
                if (currentState == OrderState.PROCESSING)
                    order.setState(OrderState.SHIPPED);
                else throw invalid(currentState, event);
                break;
            case DELIVER:
                if (currentState == OrderState.SHIPPED)
                    order.setState(OrderState.DELIVERED);
                else throw invalid(currentState, event);
                break;
            case CANCEL:
                if (currentState != OrderState.CANCELLED)
                    order.setState(OrderState.CANCELLED);
                else throw invalid(currentState, event);
                break;
        }
        return order;
    }

    private InvalidStateException invalid(OrderState current, OrderEvent event) {
        return new InvalidStateException("Event '" + event + "' not allowed in state '" + current + "'");
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public OrderState getCurrentState(Long id) {
        Order order = getOrder(id);
        if (order == null) throw new RuntimeException("Order not found");
        return order.getState();
    }

    public List<OrderEvent> getAllowedEvents(Long id) {
        Order order = getOrder(id);
        if (order == null) throw new RuntimeException("Order not found");

        OrderState currentState = order.getState();
        List<OrderEvent> events = new ArrayList<>();

        switch (currentState) {
            case NEW -> {
                events.add(OrderEvent.PROCESS);
                events.add(OrderEvent.CANCEL);
            }
            case PROCESSING -> {
                events.add(OrderEvent.SHIP);
                events.add(OrderEvent.CANCEL);
            }
            case SHIPPED -> {
                events.add(OrderEvent.DELIVER);
                events.add(OrderEvent.CANCEL);
            }
            case DELIVERED -> {
                events.add(OrderEvent.CANCEL); // if cancellation allowed post-delivery
            }
            case CANCELLED -> {
                // No events allowed
            }
        }

        return events;
    }

    public List<String> getStateHistory(Long id) {
        Order order = getOrder(id);
        if (order == null) throw new RuntimeException("Order not found");
        return order.getHistory();
    }
}
