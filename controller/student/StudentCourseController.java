package com.waaproject.registrationsystem.controller.student;

import com.waaproject.registrationsystem.business.CourseAvailable;
import com.waaproject.registrationsystem.business.EnrolledCourses;
import com.waaproject.registrationsystem.business.PreWaiver;
import com.waaproject.registrationsystem.domain.Student;
import com.waaproject.registrationsystem.service.StudentService;
import com.waaproject.registrationsystem.service.StudentServiceImpl.StudentServiceRestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class StudentCourseController {

    private StudentService studentService;

    private StudentServiceRestImpl studentServiceRest;

    @Autowired
    public StudentCourseController(StudentService studentService, StudentServiceRestImpl studentServiceRest) {
        this.studentService = studentService;
        this.studentServiceRest = studentServiceRest;
    }

    @GetMapping(value = {"/available"})
    public String available(Model model) {

        Integer studentId = (Integer) model.asMap().get("studentId");
        List<CourseAvailable> courseAvailables = studentServiceRest.getCoursesAvailable(studentId);
        model.addAttribute("courseAvailables", courseAvailables);

        return "student/course/available";
    }

    @GetMapping("/enrolled")
    public String enrolled(Model model) {

        Integer studentId = (Integer) model.asMap().get("studentId");
        List<EnrolledCourses> enrolledCourses = studentServiceRest.getEnrolledCourses(studentId);
        model.addAttribute("enrolledCourses", enrolledCourses);

        return "student/course/enrolled";
    }

    @GetMapping("/preference")
    public String setPreferences(Model model) {

        Integer studentId = (Integer) model.asMap().get("studentId");
        List<CourseAvailable> courseAvailables = studentServiceRest.getCoursesAvailable(studentId);
        model.addAttribute("courseAvailables", courseAvailables);

        return "student/course/preference";
    }

    @GetMapping("/prerequisite/waived")
    public String waivedPrerequisite(Model model) {
        Integer studentId = (Integer) model.asMap().get("studentId");

        List<PreWaiver> approvedWaivers = studentServiceRest.getApprovedWaivers(studentId);
        model.addAttribute("approvedWaivers", approvedWaivers);

        return "student/course/prerequisite/waived";
    }

    @GetMapping("/prerequisite/required")
    public String prerequisiteRequired(Model model) {
        List<PreWaiver> preWaivers = studentServiceRest.getRequiredPrerequisite();
        model.addAttribute("preWaivers", preWaivers);

        return "student/course/prerequisite/required";
    }

    @GetMapping(value = {"/student"})
    public String index(Model model, HttpServletRequest request) {

        return "student/index";
    }

    @ModelAttribute("studentId")
    public Integer getStudentId(HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("usersEmailAddress");
        Integer studentId = null;

        if (email != null) {
            Student student = studentService.findByEmail(email);
            studentId = student.getId();
        }
        System.out.println(studentId);


        return studentId;
    }
}
