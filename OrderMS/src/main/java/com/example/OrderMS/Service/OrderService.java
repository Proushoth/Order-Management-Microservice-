package com.example.OrderMS.Service;
import com.example.OrderMS.Data.Order;
import com.example.OrderMS.Data.OrderRepository;
import com.example.OrderMS.Data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InfobipSmsService smsService;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Order order) {
        // Add any additional business logic before saving the order
        Order savedOrder = orderRepository.save(order);

        // Send SMS notification using InfobipSmsService
        String message = "Your order with ID " + savedOrder.getId() + " has been placed.";
        String recipient = order.getCustomerContact(); // Assuming customerContact is the phone number
        smsService.sendSms(message, recipient);

        return savedOrder;
    }

    public Order getOrder(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(int orderId, String newStatus) {
        Order order = getOrder(orderId);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public void deleteOrder(int orderId) {
        Order order = getOrder(orderId);
        orderRepository.delete(order);
    }
}
