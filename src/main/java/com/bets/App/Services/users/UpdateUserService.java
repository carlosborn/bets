package com.bets.App.Services.users;

import com.bets.App.Models.UserModel;
import com.bets.App.Repositories.UserRepository;
import jakarta.validation.constraints.NotNull;

public class UpdateUserService {

    @NotNull
    private UserRepository repository;

    public UpdateUserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserModel execute(UserModel userModel) {
        return this.repository.save(userModel);
    }

}
