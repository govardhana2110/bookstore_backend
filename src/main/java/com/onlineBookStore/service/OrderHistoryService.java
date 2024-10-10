package com.onlineBookStore.service;

import com.onlineBookStore.model.OrderHistory; // Ensure you have this model
import com.onlineBookStore.repository.OrderHistoryRepository; // Ensure you have this repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    public List<OrderHistory> getAllOrderHistories() {
        return orderHistoryRepository.findAll();
    }

    public OrderHistory addOrderHistory(OrderHistory orderHistory) {
        return orderHistoryRepository.save(orderHistory);
    }

    // Update method that only updates the status
    public OrderHistory updateOrderStatus(Long id, String orderStatus) {
        return orderHistoryRepository.findById(id).map(orderHistory -> {
            orderHistory.setOrderStatus(orderStatus); // Only update the status
            return orderHistoryRepository.save(orderHistory);
        }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    public void deleteOrderHistory(Long id) {
        orderHistoryRepository.deleteById(id);
    }
}
