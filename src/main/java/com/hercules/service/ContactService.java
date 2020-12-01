package com.hercules.service;

import com.hercules.model.Contact;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ContactService extends CrudService<Contact, Long> {
    Optional<Contact> findContactByCaseId(Long caseId);
}
