package com.Projeto.pi.medioTec.Dto;

import com.Projeto.pi.medioTec.Config.LocalTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalTime;

public record WeekDTO(
        String classId,
        String disciplineId,
        String dayOfWeek,
        @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime startTime,
        @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime endTime,
        Long registrationId
) {
}
