package com.koboolean.my_graphql_project.service;

import com.koboolean.my_graphql_project.entity.cart.Cart;
import com.koboolean.my_graphql_project.entity.user.User;
import com.koboolean.my_graphql_project.repository.Database;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    public Cart getUserCart(String userId) throws BadRequestException{
        return Database.getInstance().carts.stream()
                .filter(cart -> cart.getUser().getId().equals(userId))
                .findFirst()
                .map(cart -> {
                    cart.setItems(Database.getInstance().cartItems.stream()
                            .filter(cartItem -> cartItem.getCart().getId().equals(cart.getId()))
                            .toList());
                    cart.setTotalAmount(cart.getItems().stream()
                            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                            .sum());
                    return cart;
                })
                .orElseThrow(() -> new BadRequestException("Cart not found"));
    }

    public Cart addUserCart(User user) {
        Cart cart = Cart.builder()
                .id(UUID.randomUUID().toString().substring(0, 5))
                .user(user)
                .build();

        Database.getInstance().carts.add(cart);

        return cart;
    }
}
