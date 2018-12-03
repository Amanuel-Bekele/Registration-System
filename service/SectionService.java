package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.domain.Section;
import com.waaproject.registrationsystem.service.presentationobjects.AdminSectionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SectionService {
    List<Section> pickSections();

    List<AdminSectionView> getSectionList(StudentSectionService service);

    Section save(Section section);

    Page<Section> getAllPaginated(Pageable pageable);
}
