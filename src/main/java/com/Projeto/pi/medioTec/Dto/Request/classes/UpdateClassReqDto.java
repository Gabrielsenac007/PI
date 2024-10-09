package com.Projeto.pi.medioTec.Dto.Request.classes;

import org.springframework.data.repository.query.Param;

public record UpdateClassReqDto( String id,
                                 String name,
                                 String schoolYear,
                                 Integer semester,
                                 String shift) {
}
