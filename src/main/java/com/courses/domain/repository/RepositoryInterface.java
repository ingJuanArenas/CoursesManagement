package com.courses.domain.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RepositoryInterface <D,DP> {
      
    Page<DP> getAll(Pageable pageable);
    D getById(Long id);
    Page<DP> getByName(String name, Pageable pageable);
    D save (D dto);
    D update(Long id, D dto);
    D updateStatus(Long id, boolean active);
    void delete(Long id);
}
