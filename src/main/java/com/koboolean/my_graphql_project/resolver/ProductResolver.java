package com.koboolean.my_graphql_project.resolver;

import com.koboolean.my_graphql_project.entity.product.Product;
import com.koboolean.my_graphql_project.input.AddProductInput;
import com.koboolean.my_graphql_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductResolver {

    private final ProductService productService;

    @QueryMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @MutationMapping
    public Product addProduct(@Argument AddProductInput addProductInput){
        return productService.addProduct(addProductInput);
    }

    @SubscriptionMapping
    public Flux<Product> newProduct(@Argument String productName){
        return productService.messageFlux(productName);
    }

}
