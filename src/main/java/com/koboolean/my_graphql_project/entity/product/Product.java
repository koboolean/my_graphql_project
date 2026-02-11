
package com.koboolean.my_graphql_project.entity.product;


import com.koboolean.my_graphql_project.entity.SearchResult;

public interface Product extends SearchResult {
    String getId();
    String getName();
    double getPrice();
    ProductType getProductType();
}
