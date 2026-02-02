package com.courses.domain.repository;

import java.util.List;


public interface RepositoryInterface <D,DP> {
      
    List<DP> getAll();
    D getById(Long id);
    List<D> getByName(String name);
    D save (D dto);
    D update(Long id, D dto);
    void delete(Long id);
}
