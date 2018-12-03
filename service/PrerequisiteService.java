package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.domain.Prerequisite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrerequisiteService {
    List<Prerequisite> getAll();

    public Page<Prerequisite> getAllPaginated(Pageable pageable);

    Prerequisite save(Prerequisite prerequisite);

    Prerequisite findById(Integer id);
}
