package com.hercules.service;

import com.hercules.model.Tool;

import java.util.Optional;

public interface ToolService extends CrudService<Tool, Long> {
    Optional<Tool> findByName(String name);
    Optional<Tool> findByToolId(Long ToolId);
}
