package com.waaproject.registrationsystem.repository;

import com.waaproject.registrationsystem.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SectionRepository extends PagingAndSortingRepository<Section,Integer> {

    List<Section> findAll();




}
