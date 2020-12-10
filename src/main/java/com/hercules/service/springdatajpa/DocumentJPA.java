package com.hercules.service.springdatajpa;

import com.hercules.model.Case;
import com.hercules.model.Document;
import com.hercules.model.Task;
import com.hercules.repository.DocumentRepository;
import com.hercules.service.DocService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DocumentJPA  implements DocService {
    private DocumentRepository repo;

    public DocumentJPA(DocumentRepository repo){
        this.repo=repo;
    }


    @Override
    public Set<Document> findAll() {
        Set<Document> set = new HashSet<>();
        repo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Document save(Document object) {
        return repo.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repo.deleteById(aLong);
    }

    @Override
    public Optional<Document> findById(Long aLong) {
        return repo.findById(aLong);
    }

}
