package com.onlineBookStore.repository;

import com.onlineBookStore.model.Book;
import com.onlineBookStore.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    List<CartItems> findByCategory(String category);

}
