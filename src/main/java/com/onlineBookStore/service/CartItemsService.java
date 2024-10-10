package com.onlineBookStore.service;

import com.onlineBookStore.model.CartItems;
import com.onlineBookStore.repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemsService {

    @Autowired
    private CartItemsRepository cartItemsRepository;

    public List<CartItems> getAllCartItems() {
        return cartItemsRepository.findAll();
    }

    public CartItems addCartItems(CartItems cartItem) {
        return cartItemsRepository.save(cartItem);
    }

    // Update method that only updates quantity
    public CartItems updateCartItems(Long id, Integer quantity) {
        return cartItemsRepository.findById(id).map(cartItem -> {
            cartItem.setQuantity(quantity); // Only update quantity
            return cartItemsRepository.save(cartItem);
        }).orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id " + id));
    }

    public void deleteCartItems(Long id) {
        cartItemsRepository.deleteById(id);
    }
}
