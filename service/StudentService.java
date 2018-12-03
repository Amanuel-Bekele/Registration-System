package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    public Student save(Student student);

    public List<Student> getAll();

    public Student findById(Integer id);

    public Page<Student> getAllPaginated(Pageable pageable);

    public Student update(Student student);

    Student getByEmail(String email);

    public Student findByEmail(String email);
}
