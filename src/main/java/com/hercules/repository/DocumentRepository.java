package com.hercules.repository;

import com.hercules.model.Document;
import com.hercules.model.Task;

import java.util.Optional;

public interface DocumentRepository {
    Optional<Document> findByTaskId(Long TaskId);
}
