package com.koboolean.my_graphql_project.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Electronics implements Product {
    private String id;
    private String name;
    private double price;
    private String warrantyPeriod;

    @Builder.Default
    private ProductType productType = ProductType.ELECTRONICS;

}
