package com.courses.persistence.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Auditable {
     @Column(name = "created_date", updatable = false)
     @JsonIgnore
     private LocalDateTime createdDate;

     @Column(name = "last_modified_date")
     @JsonIgnore
     private LocalDateTime lastModifiedDate;
}
