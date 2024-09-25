package com.Projeto.pi.medioTec.Dto.Request.Coordinator;

import com.Projeto.pi.medioTec.Dto.Request.UserRegisterRequestDto;

public record ProfessorRegisterCombineDto(UserRegisterRequestDto professor, AssDiscAndProfReqDto disciplina) {
}
