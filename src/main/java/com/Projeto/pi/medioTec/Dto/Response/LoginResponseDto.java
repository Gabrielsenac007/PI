package com.Projeto.pi.medioTec.Dto.Response;

import com.Projeto.pi.medioTec.Entity.User.UserRole;

public record LoginResponseDto(String token, String name, UserRole role) {
}
