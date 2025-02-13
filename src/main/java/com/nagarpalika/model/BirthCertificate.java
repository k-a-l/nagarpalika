package com.nagarpalika.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "birth_certificates")
public class BirthCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String childName;
    @Column(nullable = false)
    private String fatherName;
    @Column(nullable = true)
    private String motherName;
    private String dateOfBirth;
    private String placeOfBirth;
    private String gender;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status= ApplicationStatus.PENDING;
    private String rejectionReason;
    private LocalDateTime appliedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User applicant;

}
