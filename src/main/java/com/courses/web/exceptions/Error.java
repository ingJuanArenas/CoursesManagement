package com.courses.web.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error DTO")
public record Error(
    String type,
    String message
) {}
