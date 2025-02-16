package com.nagarpalika.repository;

import com.nagarpalika.model.BirthCertificate;
import com.nagarpalika.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirthCertificateRepository extends JpaRepository<BirthCertificate, Long> {

    // Find all birth certificates by application status (e.g., PENDING, APPROVED, REJECTED)
    List<BirthCertificate> findByStatus(ApplicationStatus status);

    // Find all birth certificates by applicant's name
    List<BirthCertificate> findByApplicantUsername(String username);

    // Find all birth certificates by status and applicant's name
    List<BirthCertificate> findByStatusAndApplicantUsername(ApplicationStatus status, String username);
}
