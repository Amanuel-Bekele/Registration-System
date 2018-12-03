package com.waaproject.registrationsystem.controller.admin;

import com.waaproject.registrationsystem.domain.Professor;
import com.waaproject.registrationsystem.domain.Student;
import com.waaproject.registrationsystem.repository.ProfessorRepository;
import com.waaproject.registrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/students")
public class StudentController {

    private StudentService studentService;

    private ProfessorRepository professorRepository;

    @Autowired
    public StudentController(StudentService studentService, ProfessorRepository professorRepository) {
        this.studentService = studentService;
        this.professorRepository = professorRepository;
    }

    @GetMapping(value = {"","/","/list"})
    public String list(Model model, @SortDefault("lastName") Pageable pageable){

        System.out.println(pageable);
        Page<Student> students = studentService.getAllPaginated(pageable);
        model.addAttribute("students", students);

        return "admin/student/list";
    }

    @GetMapping(value = {"/add","/create"})
    public String create(@ModelAttribute("student") Student student, Model model){

        List<Professor> professors = professorRepository.findAll();
        model.addAttribute("professors", professors);

        return "admin/student/create";
    }

    @PostMapping(value = {"/save"})
    public String save(@Valid @ModelAttribute Student student, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            List<Professor> professors = professorRepository.findAll();
            model.addAttribute("professors", professors);

            return "admin/student/create";
        }

        studentService.save(student);
        redirectAttributes.addFlashAttribute("successMessage","Successfully updated student detail.");

        return "redirect:/admin/students/list";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){

        Student student = studentService.findById(id);

        if(student == null){
            return "admin/errors/404";
        }

        List<Professor> professors = professorRepository.findAll();
        model.addAttribute("professors", professors);

        model.addAttribute("student",student);

        return "admin/student/edit";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@ModelAttribute Student student, BindingResult result, @PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            List<Professor> professors = professorRepository.findAll();
            model.addAttribute("professors", professors);

            return "admin/student/edit";
        }

        studentService.update(student);
        redirectAttributes.addFlashAttribute("successMessage","Successfully updated student detail.");
        
        return "redirect:/admin/students/list";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        System.out.println(e.getMessage());
        return "admin/errors/500";
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String methodNotAlowed(){

        return "admin/errors/405";
    }
}
