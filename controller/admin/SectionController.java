package com.waaproject.registrationsystem.controller.admin;

import com.waaproject.registrationsystem.domain.Section;
import com.waaproject.registrationsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/sections")
public class SectionController {

    @Autowired
    SectionService sectionService;
    @Autowired
    StudentSectionService studentSectionService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;


    @GetMapping(value = {"","/","/list"})
    public String index(@SessionAttribute("usersEmailAddress") String email, Model model, @SortDefault("startDate") Pageable pageable){

        Integer id=studentService.getByEmail(email).getId();


//        List<AdminSectionView> sections =sectionService.getSectionList(studentSectionService);

        Page<Section> sections = sectionService.getAllPaginated(pageable);

        model.addAttribute("sections",sections);

        return "admin/section/list";
    }


    @GetMapping(value = {"/create","/add"})
    public String create(@ModelAttribute("section") Section section, Model model){

        return "admin/section/create";
    }


    @PostMapping(value = "/save")
    public String save(@ModelAttribute Section section, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        System.out.println(section.getCourse());
        section.setCourse(courseService.findById(section.getCourse().getCourseCode()));

        System.out.println(section);

        if(result.hasErrors()){
            return "admin/section/create";
        }

        sectionService.save(section);
        redirectAttributes.addFlashAttribute("successMessage","Section added successfully.");

        return "redirect:/admin/sections/list";
    }
}
