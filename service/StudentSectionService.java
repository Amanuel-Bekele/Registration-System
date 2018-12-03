package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.domain.Section;

public interface StudentSectionService {
    int countBySectionId(Section section);
}
