package com.waaproject.registrationsystem.repository;

import com.waaproject.registrationsystem.domain.Section;
import com.waaproject.registrationsystem.domain.Student;
import com.waaproject.registrationsystem.domain.StudentSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentSectionRepository extends JpaRepository<StudentSection, Integer> {

    @Query("select s from StudentSection s where s.isWaived = ?2 and s.student=?1")
    List<StudentSection> getWaived(Student std, boolean condition);

    @Query("select s from StudentSection s where s.student =?1 and s.isWaived=null or s.isWaived=false")
    List<StudentSection> findByStudentAndIsWaivedFalse(Student student);


    List<StudentSection> findBySection(Section section);

    Integer countBySection(Section section);

}

