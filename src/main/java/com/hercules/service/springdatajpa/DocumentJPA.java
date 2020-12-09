package com.hercules.service.springdatajpa;

import com.hercules.model.Task;
import com.hercules.service.TaskService;

import java.util.Optional;
import java.util.Set;

public class DocumentJPA  implements TaskService
{
    @Override
    public Set<Task> findAll() {
        return null;
    }

    @Override
    public Task save(Task object) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        taskRepository.deleteById(aLong);
    }

    @Override
    public Optional<Task> findById(Long aLong) {
        return taskRepository.findById(aLong);
    }

    @Override
    public Optional<Task> findByTaskId(Long taskId) {
        return Optional.empty();
    }
}
