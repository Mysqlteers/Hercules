package com.hercules.service;

import com.hercules.model.JobType;

import java.util.List;

public interface JobTypeService extends CrudService<JobType, Long> {
    List<JobType> findAllByMaterialOrderByDescriptionAsc(boolean material);
}
