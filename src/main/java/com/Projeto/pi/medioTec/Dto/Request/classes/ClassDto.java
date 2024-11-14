package com.Projeto.pi.medioTec.Dto.Request.classes;

public record ClassDto(
        String id,
        String nameClass,
        String schoolYear,
        String shift,
        Integer semester
) {
}
