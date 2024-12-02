package com.Projeto.pi.medioTec.Dto.Request;

import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import org.springframework.web.multipart.MultipartFile;

public record AlunoRegisterRequestDto(String cpf, String name, String email, String password, String classeId) {
}
