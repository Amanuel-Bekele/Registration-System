package com.waaproject.registrationsystem.repository;

import com.waaproject.registrationsystem.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentByEmail(String email);

    public Student findByEmail(String email);
}
