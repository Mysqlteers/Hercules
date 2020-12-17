package com.hercules.repository;

import com.hercules.model.JobType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JobTypeRepository extends CrudRepository<JobType, Long> {
    List<JobType> findAllByMaterialOrderByDescriptionAsc(boolean material);
    Optional<JobType> findByDescription(String description);
}
