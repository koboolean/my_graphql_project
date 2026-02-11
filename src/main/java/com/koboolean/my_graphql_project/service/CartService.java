package com.koboolean.my_graphql_project.service;

import com.koboolean.my_graphql_project.entity.cart.Cart;
import com.koboolean.my_graphql_project.entity.cart.CartItem;
import com.koboolean.my_graphql_project.entity.product.Product;
import com.koboolean.my_graphql_project.entity.user.User;
import com.koboolean.my_graphql_project.input.AddCartItemInput;
import com.koboolean.my_graphql_project.input.DeleteCartItemInput;
import com.koboolean.my_graphql_project.repository.Database;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;

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

    public Cart addCartItem(AddCartItemInput addCartItemInput) throws BadRequestException {

        Cart cart = getUserCart(addCartItemInput.userId());
        Product product = productService.getProduct(addCartItemInput.productId());

        CartItem item = CartItem.builder()
                .id(UUID.randomUUID().toString().substring(0, 5))
                .product(product)
                .cart(cart)
                .build();

        Database.getInstance().cartItems.add(item);

        return cart;
    }

    public Cart deleteCartItem(DeleteCartItemInput deleteCartItemInput) throws BadRequestException {
        Database.getInstance().cartItems.removeIf(cartItem -> cartItem.getId().equals(deleteCartItemInput.cartItemId()));

        return getUserCart(deleteCartItemInput.userId());
    }
}
