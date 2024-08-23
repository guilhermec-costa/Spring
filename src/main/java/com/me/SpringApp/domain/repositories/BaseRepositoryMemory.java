package com.me.SpringApp.domain.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepositoryMemory<E, ID> {
    void insert(E entity);
    void update(ID id, E entity);
    void delete(ID id);
    Optional<E> findById(ID id);
    List<E> findAll();
}
