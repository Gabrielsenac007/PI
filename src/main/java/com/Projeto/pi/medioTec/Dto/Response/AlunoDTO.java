package com.Projeto.pi.medioTec.Dto.Response;

import com.Projeto.pi.medioTec.Entity.Teams.Classes;

public record AlunoDTO(String id,String cpf, String name, String email, String imgProfile ,Classes studentClass) {
}