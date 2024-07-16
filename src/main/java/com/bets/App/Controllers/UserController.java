package com.bets.App.Controllers;

import com.bets.App.Models.UserModel;
import com.bets.App.Repositories.UserRepository;
import com.bets.App.DTOs.users.CreateUserDTO;
import com.bets.App.Services.users.CreateUserService;
import com.bets.App.Services.users.FindUserByIdStrService;
import com.bets.App.Services.users.UpdateUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserModel createUser(@Valid CreateUserDTO createUserDTO) {
        CreateUserService createUserService = new CreateUserService(userRepository);
        return createUserService.execute(createUserDTO);
    }

    public UserModel findUserByIdStr(@NotNull String idStr) {
        FindUserByIdStrService findUserByIdStrService = new FindUserByIdStrService(userRepository);
        return findUserByIdStrService.execute(idStr);
    }

    public UserModel update(@NotNull UserModel userModel) {
        UpdateUserService updateUserService = new UpdateUserService(userRepository);
        return updateUserService.execute(userModel);
    }

}
