package com.waaproject.registrationsystem.controller.admin;

import com.waaproject.registrationsystem.domain.Block;
import com.waaproject.registrationsystem.domain.Course;
import com.waaproject.registrationsystem.domain.Professor;
import com.waaproject.registrationsystem.service.BlockService;
import com.waaproject.registrationsystem.service.CourseService;
import com.waaproject.registrationsystem.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class AdviceController {
    @Autowired
    CourseService courseService;
    @Autowired
    ProfessorService professorService;
    @Autowired
    BlockService blockService;

    @ModelAttribute("courses")
    public List<Course> getCourse(){
        return courseService.getAll();
    }

    @ModelAttribute("professorlist")
    public List<Professor> getProfessors(){
        return professorService.getAll();
    }

    @ModelAttribute("blocks")
    public List<Block> getBlocks(){
        return blockService.getAll();
    }
}

