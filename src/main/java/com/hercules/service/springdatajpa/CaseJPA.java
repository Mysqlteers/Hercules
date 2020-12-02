package com.hercules.service.springdatajpa;

import com.hercules.model.Case;
import com.hercules.repository.CaseRepository;
import com.hercules.service.CaseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaseJPA implements CaseService {
    private CaseRepository caseRepository;

    public CaseJPA(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }


    @Override
    public Set<Case> findAll() {
        Set<Case> caseSet = new HashSet<>();
        caseRepository.findAll().forEach(caseSet::add);
        return caseSet;
    }

    @Override
    public Case save(Case object) {
        System.out.println("Saving new case -From CaseJPA");
        return caseRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        caseRepository.deleteById(aLong);
    }

    @Override
    public Optional<Case> findById(Long aLong) {
        return caseRepository.findById(aLong);
    }

    @Override
    public Optional<Case> findByLocation(String location) {
        return caseRepository.findByLocation(location);
    }

    @Override
    public List<Case> findAllByOrderByStatusAscCaseIdAsc() {
        return caseRepository.findAllByOrderByStatusAscCaseIdAsc();
    }
}
