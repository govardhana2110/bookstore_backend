package com.onlineBookStore.controller;

import com.onlineBookStore.model.CartItems;
import com.onlineBookStore.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DTO class to hold quantity update
class UpdateQuantityRequest {
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

@RestController
@RequestMapping("/api/cartItems")
public class CartItemsController {

    @Autowired
    private CartItemsService cartItemsService;

    @GetMapping
    public List<CartItems> getAllCartItems() {
        return cartItemsService.getAllCartItems();
    }

    @PostMapping("/add")
    public ResponseEntity<CartItems> addCartItems(@RequestBody CartItems cartItem) {
        return new ResponseEntity<>(cartItemsService.addCartItems(cartItem), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<CartItems> updateCartItems(@PathVariable Long id, @RequestBody UpdateQuantityRequest request) {
        return new ResponseEntity<>(cartItemsService.updateCartItems(id, request.getQuantity()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItems(@PathVariable Long id) {
        cartItemsService.deleteCartItems(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
