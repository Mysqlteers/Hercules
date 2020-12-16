package com.hercules.service.springdatajpa;

import com.hercules.model.Employee;
import com.hercules.model.JobType;
import com.hercules.repository.JobTypeRepository;
import com.hercules.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobTypeJPA implements JobTypeService {
    private JobTypeRepository jr;

    public JobTypeJPA(JobTypeRepository jr) {
        this.jr = jr;
    }

    @Override
    public Set<JobType> findAll() {
        Set<JobType> set = new HashSet<>();
        jr.findAll().forEach(set::add);
        return set;
    }

    @Override
    public JobType save(JobType object) {
        return jr.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        jr.deleteById(aLong);
    }

    @Override
    public Optional<JobType> findById(Long aLong) {
        return jr.findById(aLong);
    }

    @Override
    public List<JobType> findAllByMaterialOrderByDescriptionAsc(boolean material) {
        return jr.findAllByMaterialOrderByDescriptionAsc(material);
    }
}
