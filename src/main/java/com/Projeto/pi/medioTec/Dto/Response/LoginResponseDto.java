package com.Projeto.pi.medioTec.Dto.Response;

import com.Projeto.pi.medioTec.Dto.Request.classes.ClassDto;
import com.Projeto.pi.medioTec.Entity.User.UserRole;

public record LoginResponseDto(
        String token,
        String name,
        UserRole role,
        String id,
        String classId,
        String className,
        String schoolYear,
        String shift,
        Integer semester
) {
}
