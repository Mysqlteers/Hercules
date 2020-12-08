package com.hercules.service;

import com.hercules.model.Contact;
import com.hercules.model.Document;

import java.util.Optional;

public interface DocumentService extends CrudService<Document, Long>{
    Optional<Document> findDocumentByDocumentName(String documentName);
    Optional<Document> findDocumentByCaseId(Long caseId);
}
