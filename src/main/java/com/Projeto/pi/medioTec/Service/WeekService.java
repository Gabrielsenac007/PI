package com.Projeto.pi.medioTec.Service;

import com.Projeto.pi.medioTec.Dto.WeekDTO;
import com.Projeto.pi.medioTec.Entity.Week.Week;
import com.Projeto.pi.medioTec.Entity.Teams.Classes;
import com.Projeto.pi.medioTec.Entity.Disciplines.Disciplines;
import com.Projeto.pi.medioTec.Repository.ClassesRepository;
import com.Projeto.pi.medioTec.Repository.DisciplinesRepository;
import com.Projeto.pi.medioTec.Repository.WeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeekService {

    @Autowired
    private WeekRepository weekRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private DisciplinesRepository disciplinesRepository;

    public Week registerWeek(WeekDTO weekDTO) {
        // Busca a classe pelo ID
        Classes classe = classesRepository.findById(weekDTO.classId())
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + weekDTO.classId()));

        Disciplines discipline = disciplinesRepository.findById(weekDTO.disciplineId())
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + weekDTO.disciplineId()));

        Week week = new Week();
        week.setClasse(classe);
        week.setDiscipline(discipline);
        week.setDayOfWeek(weekDTO.dayOfWeek());
        week.setStartTime(weekDTO.startTime());
        week.setEndTime(weekDTO.endTime());
        week.setRegistrationId(weekDTO.registrationId());

        // Salva no banco de dados
        return weekRepository.save(week);
    }

    public List<Week> getAllWeeks() {
        return weekRepository.findAll();
    }

    public Week getWeekById(Long id) {
        return weekRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Week not found with ID: " + id));
    }

    public List<Week> getWeeksByClassId(String classId) {
        return weekRepository.findByClasseId(classId);
    }
}
