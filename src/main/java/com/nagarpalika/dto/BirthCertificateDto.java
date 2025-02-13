package com.nagarpalika.dto;

import java.time.LocalDateTime;

public record BirthCertificateDto(
        Long id,
        String childName,
        String fatherName,
        String motherName,
        String dateOfBirth,
        String placeOfBirth,
        String gender,
        String status, // Enum as String (ApplicationStatus)
        String rejectionReason,
        LocalDateTime appliedDate,
        String applicantName // Assuming the applicant's name is included
) {}
