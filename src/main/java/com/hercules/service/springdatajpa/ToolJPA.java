package com.hercules.service.springdatajpa;

import com.hercules.model.Tool;
import com.hercules.repository.ToolRepository;
import com.hercules.service.ToolService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ToolJPA implements ToolService {
    private com.hercules.repository.ToolRepository ToolRepository;

    public ToolJPA(ToolRepository ToolRepository) {
        this.ToolRepository = ToolRepository;
    }


    @Override
    public Set<Tool> findAll() {
        Set<Tool> ToolSet = new HashSet<>();
        ToolRepository.findAll().forEach(ToolSet::add);
        return ToolSet;
    }

    @Override
    public Tool save(Tool object) {
        return ToolRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ToolRepository.deleteById(aLong);
    }

    @Override
    public Optional<Tool> findById(Long aLong) {
        return ToolRepository.findById(aLong);
    }

    @Override
    public Optional<Tool> findByName(String name) {
        return ToolRepository.findByName(name);
    }

    @Override
    public Optional<Tool> findByToolId(Long ToolId) {
        return ToolRepository.findByToolId(ToolId);
    }


    public ToolRepository getToolRepository() {
        return ToolRepository;
    }

    public void setToolRepository(ToolRepository ToolRepository) {
        this.ToolRepository = ToolRepository;
    }
}
