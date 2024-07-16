package com.bets.App.Controllers;

import com.bets.App.Models.UserModel;
import com.bets.App.Repositories.UserRepository;
import com.bets.App.DTOs.users.CreateUserDTO;
import com.bets.App.Services.users.CreateUserService;
import com.bets.App.Services.users.FindUserByIdStrService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private static UserRepository userRepository;

    public static UserModel createUser(@Valid CreateUserDTO createUserDTO) {
        CreateUserService createUserService = new CreateUserService(userRepository);
        return createUserService.execute(createUserDTO);
    }

    public static UserModel findUserByIdStr(@NotNull String idStr) {
        FindUserByIdStrService findUserByIdStrService = new FindUserByIdStrService(userRepository);
        return findUserByIdStrService.execute(idStr);
    }

}
