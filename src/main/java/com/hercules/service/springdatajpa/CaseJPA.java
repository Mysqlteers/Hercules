package com.hercules.service.springdatajpa;

import com.hercules.model.Case;
import com.hercules.repository.CaseRepository;
import com.hercules.service.CaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CaseJPA implements CaseService {
    private CaseRepository caseRepository;

    public CaseJPA(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }


    @Override
    public Set<Case> findAll() {
        return null;
    }

    @Override
    public Case save(Case object) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Case> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Case> findByLocation(String location) {
        return Optional.empty();
    }
}
