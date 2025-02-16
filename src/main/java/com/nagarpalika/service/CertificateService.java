package com.nagarpalika.service;

import com.nagarpalika.dto.BirthCertificateDto;
import com.nagarpalika.dto.BirthCertificateRequestDto;

import java.util.List;

public interface CertificateService {
    // Create new birth certificate application
    BirthCertificateDto addBirthCertificate(BirthCertificateRequestDto request);

    // Get birth certificate by ID
    BirthCertificateDto getBirthCertificate(Long id);

    // Get all birth certificates with optional filters (e.g., by status, applicant, date)
    List<BirthCertificateDto> getAllBirthCertificates(String status, String applicantName);

    // Update birth certificate details
    BirthCertificateDto updateBirthCertificate(Long id, BirthCertificateRequestDto request);

    // Delete birth certificate (soft delete or hard delete)
    void deleteBirthCertificate(Long id);

    // Approve birth certificate application
    BirthCertificateDto approveBirthCertificate(Long id);

    // Reject birth certificate application with a reason
    BirthCertificateDto rejectBirthCertificate(Long id, String rejectionReason);

    // Generate and download birth certificate as PDF
    byte[] generateBirthCertificatePDF(Long id);
}
