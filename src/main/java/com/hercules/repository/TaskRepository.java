package com.hercules.repository;


import com.hercules.model.Task;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;


public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findByTaskId(Long taskId);
}
