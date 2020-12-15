package com.hercules.service;

import com.hercules.model.Container;
import com.hercules.repository.ContainerRepository;

import java.util.Optional;

public interface ContainerService extends CrudService<Container, Long> {
    Optional<Container> findFirstByContainerName(String containerName);
}
