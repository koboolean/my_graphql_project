package com.koboolean.my_graphql_project.repository;

import com.koboolean.my_graphql_project.entity.cart.Cart;
import com.koboolean.my_graphql_project.entity.cart.CartItem;
import com.koboolean.my_graphql_project.entity.product.Clothing;
import com.koboolean.my_graphql_project.entity.product.Electronics;
import com.koboolean.my_graphql_project.entity.product.Product;
import com.koboolean.my_graphql_project.entity.user.User;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

    @Getter
    private static final Database instance = new Database();

    public final List<Product> products = new ArrayList<>(Arrays.asList(
            new Electronics(
                    "1",
                    "mac book air",
                    1000.0,
                    "3years"
            ),
            new Electronics(
                    "2",
                    "iphone 15",
                    2000.0,
                    "2years"
            ),
            new Electronics(
                    "3",
                    "samsung tv",
                    3000.0,
                    "1year"
            ),
            new Clothing(
                    "5",
                    "T-shirt",
                    3000.0,
                    "M"
            ),
            new Clothing(
                    "6",
                    "Jeans",
                    3000.0,
                    "XL"
            ),
            new Clothing(
                    "7",
                    "Dress",
                    3000.0,
                    "S"
            )
    ));

    public final List<User> users = new ArrayList<>(Arrays.asList(
            User.builder()
                    .id("1")
                    .name("John Doe")
                    .email("john.doe@example.com")
                    .createdAt(OffsetDateTime.now().minusDays(35))
                    .build()
            ,
            User.builder()
                    .id("2")
                    .name("Jane Smith")
                    .email("jane.smith@example.com")
                    .createdAt(OffsetDateTime.now().minusDays(50))
                    .build()
    ));

    public final List<Cart> carts = new ArrayList<>(Arrays.asList(
            Cart.builder()
                    .id("1")
                    .user(users.stream().filter(user -> user.getId().equals("1")).findFirst().orElse(null))
            .build()
            ,
            Cart.builder()
                    .id("2")
                    .user(users.stream().filter(user -> user.getId().equals("2")).findFirst().orElse(null))
                    .build()
    ));

    public final List<CartItem> cartItems = new ArrayList<>(Arrays.asList(
            CartItem.builder().id("1").quantity(1)
                    .product(products.stream().filter(product -> product.getId().equals("1")).findFirst().orElse(null))
                    .cart(carts.stream().filter(cart -> cart.getId().equals("1")).findFirst().orElse(null))
                    .build(),
            CartItem.builder().id("2").quantity(1)
                    .product(products.stream().filter(product -> product.getId().equals("2")).findFirst().orElse(null))
                    .cart(carts.stream().filter(cart -> cart.getId().equals("1")).findFirst().orElse(null))
                    .build(),
            CartItem.builder().id("3").quantity(1)
                    .product(products.stream().filter(product -> product.getId().equals("3")).findFirst().orElse(null))
                    .cart(carts.stream().filter(cart -> cart.getId().equals("2")).findFirst().orElse(null))
                    .build(),
            CartItem.builder().id("4").quantity(1)
                    .product(products.stream().filter(product -> product.getId().equals("4")).findFirst().orElse(null))
                    .cart(carts.stream().filter(cart -> cart.getId().equals("2")).findFirst().orElse(null))
                    .build()
    ));
}
