package com.nagarpalika.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "death_certificates")
public class DeathCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deceasedName;
    private String fatherName;
    private String motherName;
    private String dateOfDeath;
    private String placeOfDeath;
    private String causeOfDeath;
    private String rejectionReason;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status= ApplicationStatus.PENDING;
    private LocalDateTime appliedDate= LocalDateTime.now();
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User applicant;


}
