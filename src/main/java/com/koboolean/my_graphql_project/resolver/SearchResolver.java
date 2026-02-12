package com.koboolean.my_graphql_project.resolver;

import com.koboolean.my_graphql_project.entity.SearchResult;
import com.koboolean.my_graphql_project.entity.product.Product;
import com.koboolean.my_graphql_project.entity.user.User;
import com.koboolean.my_graphql_project.service.ProductService;
import com.koboolean.my_graphql_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class SearchResolver {

    private final ProductService productService;
    private final UserService userService;

    @QueryMapping
    public List<SearchResult> search(@Argument String keyword){

        List<SearchResult> result = new ArrayList<>();

        List<Product> products = productService.getProducts().stream().filter(item -> item.getName().contains(keyword)).toList();
        List<User> users = userService.getUsers().stream().filter(user -> user.getName().contains(keyword)).toList();

        result.addAll(products);
        result.addAll(users);

        return result;
    }
}
