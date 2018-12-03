package com.waaproject.registrationsystem.service.impl;

import com.waaproject.registrationsystem.domain.Course;
import com.waaproject.registrationsystem.repository.CourseRepository;
import com.waaproject.registrationsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Page<Course> getAllPaginated(Pageable pageable){
        return courseRepository.findAll(pageable);
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }
}
