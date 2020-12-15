package com.hercules.repository;

import com.hercules.model.Case;
import com.hercules.model.Tool;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ToolRepository extends CrudRepository<Tool, Long> {
    Optional<Tool> findByName(String name);
    Optional<Tool> findByToolId(Long ToolId);
}
