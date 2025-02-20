package com.nagarpalika.dto;

import com.nagarpalika.model.ApplicationStatus;
import java.time.LocalDateTime;

public record DocumentDto(
        Long id,
        String documentName,
        String documentType,
        Long uploadedBy,
        LocalDateTime uploadedAt,
        String documentUrl,
        ApplicationStatus status,
        String rejectionReason
) {}