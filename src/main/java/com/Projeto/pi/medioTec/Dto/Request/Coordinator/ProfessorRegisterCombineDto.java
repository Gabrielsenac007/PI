package com.Projeto.pi.medioTec.Dto.Request.Coordinator;

import com.Projeto.pi.medioTec.Dto.Request.UserRegisterRequestDto;

import java.util.List;

public record ProfessorRegisterCombineDto(UserRegisterRequestDto professor, List<AssDiscAndProfReqDto> disciplina) {
}
