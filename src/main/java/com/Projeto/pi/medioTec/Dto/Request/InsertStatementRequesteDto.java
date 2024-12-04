package com.Projeto.pi.medioTec.Dto.Request;

import com.Projeto.pi.medioTec.Entity.Teams.Classes;

public record InsertStatementRequesteDto(String title, String content, String creatorId, Classes classId ) {
}
