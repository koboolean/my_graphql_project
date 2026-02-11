package com.koboolean.my_graphql_project.service;

import com.koboolean.my_graphql_project.entity.product.Clothing;
import com.koboolean.my_graphql_project.entity.product.Electronics;
import com.koboolean.my_graphql_project.entity.product.Product;
import com.koboolean.my_graphql_project.input.AddProductInput;
import com.koboolean.my_graphql_project.repository.Database;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final Sinks.Many<Product> productSink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);

    public Product getProduct(String productId) throws BadRequestException {
        return Database.getInstance().products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Product not found"));

    }

    public List<Product> getProducts() {
        return Database.getInstance().products;
    }

    public Product addProduct(AddProductInput addProductInput) {
        Product product = switch(addProductInput.productType()){
            case CLOTHING -> addProductClothing(addProductInput);
            case ELECTRONICS -> addProductElectronics(addProductInput);
        };

        Database.getInstance().products.add(product);
        productSink.tryEmitNext(product);

        return product;
    }

    private Product addProductElectronics(AddProductInput addProductInput) {
        return Electronics.builder()
                .id(UUID.randomUUID().toString().substring(0, 5))
                .name(addProductInput.name())
                .price(addProductInput.price())
                .warrantyPeriod(addProductInput.warrantPeriod())
                .build();
    }

    private Product addProductClothing(AddProductInput addProductInput) {
        return Clothing.builder()
                .id(UUID.randomUUID().toString().substring(0, 5))
                .name(addProductInput.name())
                .price(addProductInput.price())
                .size(addProductInput.size())
                .build();
    }

    public Flux<Product> messageFlux(String productName) {
        return productSink.asFlux().filter(product ->
                productName == null || product.getName().toUpperCase().contains(productName.toUpperCase())
        );
    }
}
