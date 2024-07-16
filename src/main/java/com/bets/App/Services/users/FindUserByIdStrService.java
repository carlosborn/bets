package com.bets.App.Services.users;

import com.bets.App.Models.UserModel;
import com.bets.App.Repositories.UserRepository;
import jakarta.validation.constraints.NotNull;

public class FindUserByIdStrService {

    @NotNull
    private UserRepository repository;

    public FindUserByIdStrService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserModel execute(String idStr) {
        return this.repository.findByIdStr(idStr);
    }

}
