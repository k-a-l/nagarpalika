package com.nagarpalika.dto;

public record DocumentDto(
        Long id,
        String documentName,
        String documentType,
        Long uploadedBy,
        String uploadedDate,
        String DocumentUrl


) {}
