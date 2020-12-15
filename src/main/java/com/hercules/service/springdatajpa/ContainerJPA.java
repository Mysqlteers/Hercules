package com.hercules.service.springdatajpa;

import com.hercules.model.Container;
import com.hercules.repository.ContainerRepository;
import com.hercules.service.ContainerService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ContainerJPA implements ContainerService {
    private ContainerRepository cr;

    public ContainerJPA(ContainerRepository cr) {
        this.cr = cr;
    }

    @Override
    public Optional<Container> findFirstByContainerName(String containerName) {
        return cr.findFirstByContainerName(containerName);
    }

    @Override
    public Set<Container> findAll() {
        Set<Container> set = new HashSet<>();
        cr.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Container save(Container object) {
        return cr.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        cr.deleteById(aLong);
    }

    @Override
    public Optional<Container> findById(Long aLong) {
        return cr.findById(aLong);
    }
}
