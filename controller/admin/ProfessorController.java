package com.waaproject.registrationsystem.controller.admin;

import com.waaproject.registrationsystem.domain.Professor;
import com.waaproject.registrationsystem.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/professors")
public class ProfessorController {

    private ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(value = {"","/","/list","/index"})
    public String index(Model model, @SortDefault("lastName") Pageable pageable){

        Page<Professor> pProfessors = professorService.getAllPaginated(pageable);
        model.addAttribute("pProfessors",pProfessors);

        return "admin/professor/list";
    }

    @GetMapping(value = {"/add","/create"})
    public String create(@ModelAttribute("professor") Professor professor, Model model){

        return "admin/professor/create";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute Professor professor, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "admin/professor/create";
        }

        professorService.save(professor);
        redirectAttributes.addFlashAttribute("successMessage","Professor added successfully.");

        return "redirect:/admin/professors/list";
    }
}
