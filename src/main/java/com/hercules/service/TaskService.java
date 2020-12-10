package com.hercules.service;

import com.hercules.model.Task;

import java.util.Optional;

public interface TaskService extends CrudService<Task, Long> {
    Optional<Task> findByName(String name);
    Optional<Task> findByTaskId(Long taskId);
}
