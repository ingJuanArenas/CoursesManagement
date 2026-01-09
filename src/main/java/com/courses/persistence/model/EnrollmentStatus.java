package com.courses.persistence.model;

import java.util.Arrays;

public enum EnrollmentStatus {
    ENROLLED, CANCELLED, COMPLETED;


 public static EnrollmentStatus fromString(String status) {
        return Arrays.stream(values())
                .filter(s -> s.name().equalsIgnoreCase(status)).findFirst().orElseThrow(
                    () -> new IllegalArgumentException("Status not found: "+ status ));

    }
}
