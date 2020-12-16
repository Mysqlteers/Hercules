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
    private ToolRepository toolRepository;

    public ToolJPA(com.hercules.repository.ToolRepository ToolRepository) {
        this.toolRepository = ToolRepository;
    }


    @Override
    public Set<Tool> findAll() {
        Set<Tool> toolSet = new HashSet<>();
        toolRepository.findAll().forEach(toolSet::add);
        return toolSet;
    }

    @Override
    public Tool save(Tool object) {
        return toolRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        toolRepository.deleteById(aLong);
    }

    @Override
    public Optional<Tool> findById(Long aLong) {
        return toolRepository.findById(aLong);
    }

    public ToolRepository getToolRepository() {
        return toolRepository;
    }

    public void setToolRepository(ToolRepository ToolRepository) {
        this.toolRepository = ToolRepository;
    }
}
