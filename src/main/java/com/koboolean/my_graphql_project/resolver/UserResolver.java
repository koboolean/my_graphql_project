package com.koboolean.my_graphql_project.resolver;

import com.koboolean.my_graphql_project.entity.user.User;
import com.koboolean.my_graphql_project.input.AddUserInput;
import com.koboolean.my_graphql_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserResolver {

    private final UserService userService;

    @QueryMapping
    public User getUser(@Argument String userId) throws BadRequestException {
        return userService.getUser(userId);
    }

    @MutationMapping
    public User addUser(@Argument AddUserInput addUserInput){
        return userService.addUser(addUserInput);
    }
}
