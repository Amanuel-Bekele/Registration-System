package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.domain.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfessorService {

    public Professor save(Professor professor);

    public List<Professor> getAll();

    public Professor findById(Integer id);

    public Page<Professor> getAllPaginated(Pageable pageable);
}
