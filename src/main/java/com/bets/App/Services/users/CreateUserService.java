package com.bets.App.Services.users;

import com.bets.App.DTOs.users.CreateUserDTO;
import com.bets.App.Models.UserModel;
import com.bets.App.Repositories.UserRepository;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CreateUserService {

    @NotNull
    private UserRepository repository;

    public CreateUserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserModel execute(CreateUserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setUserIdPlatform(userDTO.idUser());
        userModel.setRank(userDTO.rank());
        userModel.setUsername(userDTO.username());
        userModel.setIdStr(userDTO.idStr());
        userModel.setCreatedAt(new Date());

        userModel = this.repository.save(userModel);

        return userModel;
    }

}
