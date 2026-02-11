package com.koboolean.my_graphql_project.input;

import com.koboolean.my_graphql_project.entity.product.ProductType;

public record AddProductInput(
        String name,
        Float price,
        ProductType productType,
        String warrantPeriod,
        String size
) {
}
