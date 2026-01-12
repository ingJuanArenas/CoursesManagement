package com.courses.domain.repository;

import java.util.List;


public interface RepositoryInterface <D> {
      
    List<D> getAll();
    D getById(Long id);
    List<D> getByName(String name);
    List<D> getActive();
    D save (D dto);
    D update(Long id, D dto);
    void delete(Long id);
}
