package com.hercules.service;

import com.hercules.model.Contact;
import com.hercules.model.Document;

import java.util.Optional;

public interface DocService extends CrudService<Document, Long> {
    Optional<Document> findById(Long Id);
}
