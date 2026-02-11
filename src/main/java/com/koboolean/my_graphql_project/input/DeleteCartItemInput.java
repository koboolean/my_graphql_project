package com.koboolean.my_graphql_project.input;

public record DeleteCartItemInput(
        String userId,
        String cartItemId
) {
}
