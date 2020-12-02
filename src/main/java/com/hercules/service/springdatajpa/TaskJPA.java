package com.hercules.service.springdatajpa;

import com.hercules.model.Case;
import com.hercules.model.Task;
import com.hercules.repository.CaseRepository;
import com.hercules.repository.TaskRepository;
import com.hercules.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskJPA implements TaskService {
    private TaskRepository taskRepository;

    public TaskJPA(TaskRepository taskRepository){this.taskRepository = taskRepository; }


    @Override
    public Set<Task> findAll() {
            Set<Task> taskSet = new HashSet<>();
            taskRepository.findAll().forEach(taskSet::add);
            return taskSet;
    }

    @Override
    public Task save(Task object) {return taskRepository.save(object); }

    @Override
    public void deleteById(Long aLong) { taskRepository.deleteById(aLong); }

    @Override
    public Optional<Task> findById(Long aLong) { return taskRepository.findById(aLong); }

    @Override
    public Optional<Case> findByTaskId(Long taskId) { return taskRepository.findByTaskId(taskId); }
}
