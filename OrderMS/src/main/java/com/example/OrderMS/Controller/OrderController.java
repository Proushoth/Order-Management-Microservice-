package com.example.OrderMS.Controller;

import com.example.OrderMS.Data.Order;
import com.example.OrderMS.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable int orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{orderId}")
    public Order updateOrderStatus(@PathVariable int orderId, @RequestParam String newStatus) {
        return orderService.updateOrderStatus(orderId, newStatus);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }
}
