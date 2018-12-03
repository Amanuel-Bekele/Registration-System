package com.waaproject.registrationsystem.service.impl;

import com.waaproject.registrationsystem.domain.Prerequisite;
import com.waaproject.registrationsystem.repository.PrerequisiteRepository;
import com.waaproject.registrationsystem.service.PrerequisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrerequisiteServiceImpl implements PrerequisiteService {

    private PrerequisiteRepository prerequisiteRepository;

    @Autowired
    public PrerequisiteServiceImpl(PrerequisiteRepository prerequisiteRepository) {
        this.prerequisiteRepository = prerequisiteRepository;
    }

    @Override
    public List<Prerequisite> getAll() {
        return prerequisiteRepository.findAll();
    }

    @Override
    public Page<Prerequisite> getAllPaginated(Pageable pageable) {
        return prerequisiteRepository.findAll(pageable);
    }

    @Override
    public Prerequisite save(Prerequisite prerequisite) {
        return prerequisiteRepository.save(prerequisite);
    }

    @Override
    public Prerequisite findById(Integer id) {
        return prerequisiteRepository.findById(id).orElse(null);
    }
}
