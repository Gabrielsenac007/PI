package com.Projeto.pi.medioTec.Controllers;

import com.Projeto.pi.medioTec.Dto.WeekDTO;
import com.Projeto.pi.medioTec.Entity.Week.Week;
import com.Projeto.pi.medioTec.Service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/week")
public class WeekController {

    @Autowired
    private WeekService weekService;

    @PostMapping("/register")
    public ResponseEntity<Week> registerWeek(@RequestBody WeekDTO weekDTO) {
        Week createdWeek = weekService.registerWeek(weekDTO);
        return new ResponseEntity<>(createdWeek, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Week>> getAllWeeks() {
        List<Week> weeks = weekService.getAllWeeks();
        return ResponseEntity.ok(weeks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Week> getWeekById(@PathVariable Long id) {
        Week week = weekService.getWeekById(id);
        return ResponseEntity.ok(week);
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Week>> getWeeksByClassId(@PathVariable String classId) {
        List<Week> weeks = weekService.getWeeksByClassId(classId);
        return ResponseEntity.ok(weeks);
    }

}
