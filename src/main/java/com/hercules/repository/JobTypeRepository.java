package com.hercules.repository;

import com.hercules.model.JobType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobTypeRepository extends CrudRepository<JobType, Long> {
    List<JobType> findAllByMaterialOrderByDescriptionAsc(boolean material);
}
