package com.waaproject.registrationsystem.controller.admin;

import com.waaproject.registrationsystem.domain.Course;
import com.waaproject.registrationsystem.domain.PreCourse;
import com.waaproject.registrationsystem.domain.Prerequisite;
import com.waaproject.registrationsystem.service.CourseService;
import com.waaproject.registrationsystem.service.PrerequisiteService;
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
@RequestMapping("/admin/courses")
public class CourseController {

    private CourseService courseService;

    private PrerequisiteService prerequisiteService;

    @Autowired
    public CourseController(CourseService courseService, PrerequisiteService prerequisiteService) {
        this.courseService = courseService;
        this.prerequisiteService = prerequisiteService;
    }

    @GetMapping(value = {"","/","/list","/index"})
    public String index(Model model,@SortDefault("courseName") Pageable pageable){

        Page<Course> pCourses = courseService.getAllPaginated(pageable);
        model.addAttribute("pCourses", pCourses);

        return "admin/course/list";
    }

    @GetMapping(value = {"/create","/add"})
    public String create(@ModelAttribute("course") Course course, Model model){

        return "admin/course/create";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute Course course, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "admin/course/create";
        }

        courseService.save(course);
        redirectAttributes.addFlashAttribute("successMessage","Course added successfully.");

        return "redirect:/admin/courses/list";
    }


    @GetMapping(value = "/prerequisites/create")
    public String prerequisite(@ModelAttribute("prerequisite") PreCourse prerequisite, Model model){

        return "/admin/course/prerequisite";
    }

    @PostMapping(value = "/prerequisites/save")
    public String save(@ModelAttribute("prerequisite") PreCourse preCourse, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        Prerequisite prerequisite = new Prerequisite();
        prerequisite.setPrerequisite(preCourse.getCourse());
        prerequisite.setCourseList(preCourse.getPrerequisites());
        if(result.hasErrors()){
            return "admin/course/create";
        }

        prerequisiteService.save(prerequisite);
        redirectAttributes.addFlashAttribute("successMessage","Course added successfully.");

        return "redirect:/admin/courses/list";
    }
}
