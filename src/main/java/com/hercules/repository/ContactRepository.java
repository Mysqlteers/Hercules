package com.hercules.repository;

import com.hercules.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    Optional<Contact> findContactByCaseId(Long caseId);
}
