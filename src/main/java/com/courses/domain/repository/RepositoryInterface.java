package com.courses.domain.repository;


import org.springframework.data.domain.Page;


public interface RepositoryInterface <D,DP> {
      
    Page<DP> getAll(int page, int size);
    D getById(Long id);
    Page<DP> getByName(String name, int page, int size);
    D save (D dto);
    D update(Long id, D dto);
    void delete(Long id);
}
