package com.waaproject.registrationsystem.service.impl;

import com.waaproject.registrationsystem.domain.Professor;
import com.waaproject.registrationsystem.repository.ProfessorRepository;
import com.waaproject.registrationsystem.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    @Override
    public Professor findById(Integer id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Professor> getAllPaginated(Pageable pageable) {
        return professorRepository.findAll(pageable);
    }
}
