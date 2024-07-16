package com.bets.App.DTOs.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotNull
@NotBlank
public record CreateUserDTO(Long idUser, String idStr, String username, String rank) {

}
