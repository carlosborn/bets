package com.bets.App.Repositories;

import com.bets.App.Models.CrashRoundModel;
import com.bets.App.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<UserModel, String> {

    UserModel save(UserModel userModel);

    UserModel findByUserIdPlatform(Long userId);

    UserModel findByIdStr(String idStr);

}
