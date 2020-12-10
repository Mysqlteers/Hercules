package com.hercules.service;

import com.hercules.model.Case;

import java.util.List;
import java.util.Optional;

public interface CaseService extends CrudService<Case, Long>{
    Optional<Case> findByLocation(String location);
    List<Case> findAllByOrderByStatusAscCaseIdAsc();

}
