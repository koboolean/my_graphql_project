package com.koboolean.my_graphql_project.entity.cart;

import com.koboolean.my_graphql_project.entity.user.User;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private String id;
    private User user;
    private double totalAmount = 0.0;
    private List<CartItem> items = List.of();
}
