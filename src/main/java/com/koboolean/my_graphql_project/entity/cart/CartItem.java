package com.koboolean.my_graphql_project.entity.cart;

import com.koboolean.my_graphql_project.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private String id;
    private int quantity;
    private Product product;
    private Cart cart;
}
