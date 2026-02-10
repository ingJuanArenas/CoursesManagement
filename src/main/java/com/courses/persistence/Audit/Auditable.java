package com.courses.persistence.Audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Auditable {
     @Column(name = "created_date", updatable = false)
     @JsonIgnore
     @CreatedDate
     private LocalDateTime createdDate;

     @Column(name = "last_modified_date")
     @JsonIgnore
     @LastModifiedDate
     private LocalDateTime lastModifiedDate;
}
