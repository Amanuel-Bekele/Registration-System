package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.domain.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BlockService  {
    List<Block> getAll();

    Block save(Block block);

    Block findById(Integer id);

    Page<Block> getAllPaginated(Pageable pageable);
}
