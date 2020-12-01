package com.hercules.repository;

import com.hercules.model.Case;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CaseRepository extends CrudRepository<Case, Long> {
    Optional<Case> findByLocation(String location);

    List<Case> findAllByOrderByStatusAsc();
}
