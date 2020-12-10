package com.hercules.repository;

import com.hercules.model.Contact;
import com.hercules.model.Document;
import com.hercules.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DocumentRepository extends CrudRepository<Document, Long> {
    Optional<Document> findById(Long DocsId);
}
