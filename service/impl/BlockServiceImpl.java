package com.waaproject.registrationsystem.service.impl;

import com.waaproject.registrationsystem.domain.Block;
import com.waaproject.registrationsystem.repository.BlockRepository;
import com.waaproject.registrationsystem.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    BlockRepository blockRepository;

    @Override
    public List<Block> getAll() {
        return blockRepository.findAll();
    }

    @Override
    public Block save(Block block) {
        return blockRepository.save(block);
    }

    @Override
    public Block findById(Integer id) {
        return blockRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Block> getAllPaginated(Pageable pageable) {
        return blockRepository.findAll(pageable);
    }
}
