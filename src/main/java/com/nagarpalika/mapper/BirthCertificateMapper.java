package com.nagarpalika.mapper;

import com.nagarpalika.dto.BirthCertificateDto;
import com.nagarpalika.dto.BirthCertificateRequestDto;
import com.nagarpalika.model.BirthCertificate;
import com.nagarpalika.model.ApplicationStatus;
import com.nagarpalika.model.User;

import java.time.LocalDateTime;

public class BirthCertificateMapper {

    // Convert Entity to DTO
    public static BirthCertificateDto toBirthCertificateDto(BirthCertificate birthCertificate) {
        if (birthCertificate == null) {
            return null;
        }
        return new BirthCertificateDto(
                birthCertificate.getId(),
                birthCertificate.getChildName(),
                birthCertificate.getFatherName(),
                birthCertificate.getMotherName(),
                birthCertificate.getDateOfBirth(),
                birthCertificate.getPlaceOfBirth(),
                birthCertificate.getGender(),
                birthCertificate.getStatus().toString(),
                birthCertificate.getRejectionReason(),
                birthCertificate.getAppliedDate(),
                birthCertificate.getApplicant() != null ? birthCertificate.getApplicant().getUsername() : null
        );
    }

    // Convert Request DTO to Entity for Creating New Birth Certificate
    public static BirthCertificate fromRequestDto(BirthCertificateRequestDto requestDto, User applicant) {
        if (requestDto == null || applicant == null) {
            throw new IllegalArgumentException("Request DTO or Applicant cannot be null");
        }

        BirthCertificate birthCertificate = new BirthCertificate();
        birthCertificate.setChildName(requestDto.getChildName());
        birthCertificate.setFatherName(requestDto.getFatherName());
        birthCertificate.setMotherName(requestDto.getMotherName());
        birthCertificate.setDateOfBirth(requestDto.getDateOfBirth());
        birthCertificate.setPlaceOfBirth(requestDto.getPlaceOfBirth());
        birthCertificate.setGender(requestDto.getGender());
        birthCertificate.setStatus(ApplicationStatus.PENDING); // Default status
        birthCertificate.setApplicant(applicant);
        birthCertificate.setAppliedDate(LocalDateTime.now()); // Set current timestamp
        return birthCertificate;
    }

    // Update existing BirthCertificate entity from Request DTO
    public static void updateFromRequestDto(BirthCertificate birthCertificate, BirthCertificateRequestDto requestDto) {
        if (birthCertificate == null || requestDto == null) {
            throw new IllegalArgumentException("BirthCertificate or Request DTO cannot be null");
        }

        birthCertificate.setChildName(requestDto.getChildName());
        birthCertificate.setFatherName(requestDto.getFatherName());
        birthCertificate.setMotherName(requestDto.getMotherName());
        birthCertificate.setDateOfBirth(requestDto.getDateOfBirth());
        birthCertificate.setPlaceOfBirth(requestDto.getPlaceOfBirth());
        birthCertificate.setGender(requestDto.getGender());
        // Applied date should not change on update
    }
}
