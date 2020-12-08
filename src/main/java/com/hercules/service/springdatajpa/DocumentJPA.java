package com.hercules.service.springdatajpa;

import com.hercules.model.Contact;
import com.hercules.model.Document;
import com.hercules.repository.DocumentRepository;
import com.hercules.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DocumentJPA implements DocumentService {
    private DocumentRepository dr;

    public DocumentJPA(DocumentRepository dr) { this.dr = dr; }

    @Override
    public Set<Document> findAll(){
        Set<Document> documentSet = new HashSet<>();
        dr.findAll().forEach(documentSet::add);
        return documentSet;
    }

    @Override
    public Document save(Document object) { return dr.save(object); }

    @Override
    public void deleteById(Long aLong) { dr.deleteById(aLong);}

    @Override
    public Optional<Document> findById(Long aLong) { return dr.findById(aLong); }


    @Override
    public Optional<Document> findDocumentByDocumentName(String documentName) { return dr.findDocumentByDocumentName(documentName); }

    @Override
    public Optional<Document> findDocumentByCaseId(Long aLong) { return dr.findDocumentByCaseId(aLong); }
}
