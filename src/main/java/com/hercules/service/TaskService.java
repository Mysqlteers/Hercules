package com.hercules.service;

import com.hercules.model.Task;

import java.util.Optional;

public interface TaskService extends CrudService<Task, Long> {
    Optional<Task> findByTaskId(Long taskId);
}
