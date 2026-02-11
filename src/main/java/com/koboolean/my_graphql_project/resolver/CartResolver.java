package com.koboolean.my_graphql_project.resolver;

import com.koboolean.my_graphql_project.entity.cart.Cart;
import com.koboolean.my_graphql_project.entity.cart.CartItem;
import com.koboolean.my_graphql_project.input.AddCartItemInput;
import com.koboolean.my_graphql_project.input.DeleteCartItemInput;
import com.koboolean.my_graphql_project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CartResolver {

    private final CartService cartService;

    @QueryMapping
    public Cart getUserCart(@Argument String userId) throws BadRequestException {
        return cartService.getUserCart(userId);
    }

    @MutationMapping
    public Cart addCartItem(@Argument AddCartItemInput addCartItemInput) throws BadRequestException {
        return cartService.addCartItem(addCartItemInput);
    }

    @MutationMapping
    public Cart deleteCartItem(@Argument DeleteCartItemInput deleteCartItemInput) throws BadRequestException {
        return cartService.deleteCartItem(deleteCartItemInput);
    }

}
