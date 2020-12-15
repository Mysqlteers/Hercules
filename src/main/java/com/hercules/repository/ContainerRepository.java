package com.hercules.repository;

import com.hercules.model.Container;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContainerRepository extends CrudRepository<Container, Long> {
    Optional<Container> findFirstByContainerName(String containerName);
}
