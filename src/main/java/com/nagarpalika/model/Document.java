package com.nagarpalika.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;
    private String documentType;

    @Lob
    private byte[] data;

    private String documentUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User uploadedBy;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    private String rejectionReason;
}