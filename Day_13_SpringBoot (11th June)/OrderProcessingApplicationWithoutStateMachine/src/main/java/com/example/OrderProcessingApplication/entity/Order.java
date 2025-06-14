package com.example.OrderProcessingApplication.entity;

import com.example.OrderProcessingApplication.enums.OrderState;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private String description;
    private OrderState state;
    private List<String> history = new ArrayList<>();

    public Order(Long id, String description) {
        this.id = id;
        this.description = description;
        this.state = OrderState.NEW;
        this.history.add("NEW");
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public OrderState getState() { return state; }

    public void setState(OrderState state) {
        this.state = state;
        this.history.add(state.name());
    }

    public List<String> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", history=" + history +
                '}';
    }
}
