package com.waaproject.registrationsystem.service.impl;

import com.waaproject.registrationsystem.domain.Section;
import com.waaproject.registrationsystem.repository.StudentSectionRepository;
import com.waaproject.registrationsystem.service.StudentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class StudentSectionServiceImp implements StudentSectionService {

    @Autowired
    StudentSectionRepository studentSectionRepository;

    public int countBySectionId(Section section){
       return studentSectionRepository.countBySection(section);
    }
}
