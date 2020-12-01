package com.hercules.service;


import java.util.Optional;
import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> findAll();
    T save(T object);
    void deleteById(ID id);
    Optional<T> findById(ID id);
    Optional<T> findByLocation(String location);
}
