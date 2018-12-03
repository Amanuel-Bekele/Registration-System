package com.waaproject.registrationsystem.repository;

import com.waaproject.registrationsystem.domain.Block;
import com.waaproject.registrationsystem.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    List<Professor> findAll();


}
