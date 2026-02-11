package com.koboolean.my_graphql_project.input;

public record AddCartItemInput(
        String userId,
        String productId,
        int quantity
) {
}
