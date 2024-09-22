package com.Projeto.pi.medioTec.Dto.Request;

import com.Projeto.pi.medioTec.Entity.User.UserRole;

public record UserRegisterRequestDto(String cpf, String name, String email, String password) {
}
