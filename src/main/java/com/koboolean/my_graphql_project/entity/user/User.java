package com.koboolean.my_graphql_project.entity.user;

import com.koboolean.my_graphql_project.entity.SearchResult;
import com.koboolean.my_graphql_project.entity.cart.Cart;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements SearchResult {
    private String id;
    private String name;
    private String email;
    private OffsetDateTime createdAt;
    private Cart cart;
}
