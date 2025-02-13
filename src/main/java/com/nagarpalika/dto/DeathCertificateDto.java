package com.nagarpalika.dto;

public record DeathCertificateDto(
        Long id,
        String deceasedName,
        String fatherName,
        String motherName,
        String dateOfBirth,
        String placeOfBirth,
        String causeOfDeath,
        String status, // Enum as String (ApplicationStatus)
        String rejectionReason,
        String appliedDate,
        String applicantName // Assuming the applicant's name is included{
){}
