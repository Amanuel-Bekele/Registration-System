package com.waaproject.registrationsystem.service;


import com.waaproject.registrationsystem.domain.Course;
import com.waaproject.registrationsystem.domain.Prerequisite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    List<Course> getAll();

    public Page<Course> getAllPaginated(Pageable pageable);

    Course save(Course course);

    Course findById(Integer id);

}
