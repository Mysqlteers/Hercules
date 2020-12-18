package com.hercules.repository;

import com.hercules.model.Contact;
import com.hercules.model.Container;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContainerRepository extends CrudRepository<Container, Long> {
    Optional<Container> findFirstByContainerName(String containerName);
    List<Container> findAllByContactOrderByContainerIdDesc(Contact contact);
}
