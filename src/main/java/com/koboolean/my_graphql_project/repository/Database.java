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
            Electronics.builder()
                    .id("1")
                    .name("mac book air")
                    .price(1000.0)
                    .warrantyPeriod("1years")
                    .build(),
            Electronics.builder()
                    .id("2")
                    .name("iphone 15")
                    .price(2000.0)
                    .warrantyPeriod("2years")
                    .build(),
            Electronics.builder()
                    .id("3")
                    .name("mac book air")
                    .price(3000.0)
                    .warrantyPeriod("3years")
                    .build(),
            Clothing.builder()
                    .id("4")
                    .name("T-shirt")
                    .price(1000.0)
                    .size("M")
                    .build(),
            Clothing.builder()
                    .id("5")
                    .name("Jeans")
                    .price(2000.0)
                    .size("L")
                    .build(),
            Clothing.builder()
                    .id("6")
                    .name("Dress")
                    .price(3000.0)
                    .size("S")
                    .build(),
            Clothing.builder()
                    .id("7")
                    .name("hat")
                    .price(4000.0)
                    .size("M")
                    .build()
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
