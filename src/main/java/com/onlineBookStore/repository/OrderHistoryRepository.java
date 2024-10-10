package com.onlineBookStore.repository;

import com.onlineBookStore.model.CartItems;
import com.onlineBookStore.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory ,Long> {
    List<OrderHistory> findByCategory(String category);

}
