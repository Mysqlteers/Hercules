package com.hercules.service;

import com.hercules.model.JobType;

import java.util.List;
import java.util.Optional;

public interface JobTypeService extends CrudService<JobType, Long> {
    List<JobType> findAllByMaterialOrderByDescriptionAsc(boolean material);
    Optional<JobType> findByDescription(String description);
}
