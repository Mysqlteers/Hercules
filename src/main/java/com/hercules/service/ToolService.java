package com.hercules.service;

import com.hercules.model.Tool;

import java.util.Optional;

public interface ToolService extends CrudService<Tool, Long> {
    Optional<Tool> findByToolId(Long ToolId);
}
