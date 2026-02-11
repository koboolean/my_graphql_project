package com.koboolean.my_graphql_project.service;

import com.koboolean.my_graphql_project.entity.cart.Cart;
import com.koboolean.my_graphql_project.entity.user.User;
import com.koboolean.my_graphql_project.input.AddUserInput;
import com.koboolean.my_graphql_project.repository.Database;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CartService cartService;

    public User getUser(String userId) throws BadRequestException{
        return Database.getInstance().users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .map((user) -> {
                    try{
                        Cart userCart = cartService.getUserCart(userId);
                        user.setCart(userCart);
                        return user;
                    }catch (BadRequestException e){
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new BadRequestException("User Not Found"));
    }

    public User addUser(AddUserInput addUserInput) {
        User user = User.builder()
                .id(UUID.randomUUID().toString().substring(0, 5))
                .name(addUserInput.name())
                .email(addUserInput.email())
                .createdAt(OffsetDateTime.now())
                .build();

        Cart cart = cartService.addUserCart(user);

        Database.getInstance().users.add(user);
        user.setCart(cart);

        return user;
    }
}
