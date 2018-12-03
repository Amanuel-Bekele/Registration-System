package com.waaproject.registrationsystem.service.impl;

import com.waaproject.registrationsystem.domain.Section;
import com.waaproject.registrationsystem.repository.SectionRepository;
import com.waaproject.registrationsystem.service.SectionService;
import com.waaproject.registrationsystem.service.StudentSectionService;
import com.waaproject.registrationsystem.service.presentationobjects.AdminSectionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SectionServiceImp implements SectionService {
    @Autowired
    SectionRepository sectionRepository;

    public List<Section> pickSections(){
        return sectionRepository.findAll();
    }

    public List<AdminSectionView> getSectionList(StudentSectionService service){

        List<Section> sectionList=sectionRepository.findAll();
        List<AdminSectionView> sections=new ArrayList<>();

        for(Section sect:sectionList) {
            AdminSectionView sview=new AdminSectionView();
            sview.setCourseCode(sect.getCourse().getCourseCode());
            sview.setCourseTitle(sect.getCourse().getCourseDesc());
            sview.setBlock(sect.getBlock().getMonth() + ", " + sect.getBlock().getYear());
            sview.setProfessor(sect.getProfessor().getFirstName());
            sview.setCapacity(sect.getCapacity());
            sview.setAvailable(sect.getCapacity()-service.countBySectionId(sect));
            sview.setLocation(sect.getLocation());

            sections.add(sview);


        }

        return sections;
    }

    public Section save(Section section){
        return sectionRepository.save(section);
    }

    @Override
    public Page<Section> getAllPaginated(Pageable pageable) {
        return sectionRepository.findAll(pageable);
    }
}
