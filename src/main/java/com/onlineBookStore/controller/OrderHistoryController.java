package com.onlineBookStore.controller;

import com.onlineBookStore.model.OrderHistory; // Ensure this import is correct
import com.onlineBookStore.service.OrderHistoryService; // Import your OrderHistoryService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DTO class to hold status update
class UpdateOrderStatusRequest {
    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatusStatus(String orderStatustatus) {
        this.orderStatus = orderStatustatus;
    }
}

@RestController
@RequestMapping("/api/orderHistory")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping
    public List<OrderHistory> getAllOrderHistories() {
        return orderHistoryService.getAllOrderHistories();
    }

    @PostMapping("/add")
    public ResponseEntity<OrderHistory> addOrderHistory(@RequestBody OrderHistory orderHistory) {
        return new ResponseEntity<>(orderHistoryService.addOrderHistory(orderHistory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/orderStatus")
    public ResponseEntity<OrderHistory> updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest request) {
        return new ResponseEntity<>(orderHistoryService.updateOrderStatus(id, request.getOrderStatus()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderHistory(@PathVariable Long id) {
        orderHistoryService.deleteOrderHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
