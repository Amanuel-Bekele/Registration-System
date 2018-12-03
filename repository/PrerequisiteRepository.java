package com.waaproject.registrationsystem.repository;

import com.waaproject.registrationsystem.domain.Course;
import com.waaproject.registrationsystem.domain.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Integer> {

    @Query("select distinct (p.courseList) from Prerequisite p where p.isRequired=true")
    List<Course> findRequired();
}
