package com.waaproject.registrationsystem.repository;

import com.waaproject.registrationsystem.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer> {

    List<Block> findAll();

    Block findByYearAndMonth(String year, String month);

    @Query("SELECT b FROM Block b where b.month > ?1 and b.year > ?2")
    List<Block> getUpcomingBlocks(String month, String year);
}
