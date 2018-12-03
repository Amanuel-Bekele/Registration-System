package com.waaproject.registrationsystem.controller.student;

import com.waaproject.registrationsystem.business.*;
import com.waaproject.registrationsystem.domain.Block;
import com.waaproject.registrationsystem.service.BlockService;
import com.waaproject.registrationsystem.service.StudentServiceImpl.StudentServiceRestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentCourseRestController {

    private final StudentServiceRestImpl service;

    private BlockService blockService;

    @Autowired
    public StudentCourseRestController(StudentServiceRestImpl service, BlockService blockService) {
        this.service = service;
        this.blockService = blockService;
    }

    @GetMapping(value = "/courses/available/{id}")
    public @ResponseBody List<AvailableCourse> getAvailableCourses(@PathVariable int id){

        return service.getAvailableCourses(id);
    }

    @PreAuthorize(value = "hasRole('STUDENT')")
    @GetMapping(value = "/courses/enrolled/{id}")
    public @ResponseBody List<EnrolledCourses> getEnrolledCourses(@PathVariable int id){
        return service.getEnrolledCourses(id);
    }

    @PreAuthorize(value = "hasRole('STUDENT')")
    @GetMapping(value = "/courses/prerequisites/waived/{id}")
    public @ResponseBody List<PreWaiver> getApprovedWaivers(@PathVariable int id){

        return service.getApprovedWaivers(id);

    }

    @PreAuthorize(value = "hasRole('STUDENT')")
    @GetMapping(value = "/courses/prerequisite/required")
    public @ResponseBody List<PreWaiver> getRequiredPrerequisite(){

        return service.getRequiredPrerequisite();

    }

    @PreAuthorize(value = "hasRole('STUDENT')")
    @PostMapping(value = "/course/preferences/{id}")
    public void addPreferences(@RequestBody List<PreferenceBusiness> preferences, @PathVariable int id){
        service.addPreference(id, preferences);

    }

    @PreAuthorize(value = "hasRole('STUDENT')")
    @GetMapping(value = "/course/preferences/{id}")
    public List<AvailableCourse> getPreferences(@PathVariable int id){
        return service.getPreferences(id);    }


    @GetMapping(value = "/blocks")
    public List<CourseAvailable> getBlocks(){
        return service.getCoursesAvailable(34);
    }
}
