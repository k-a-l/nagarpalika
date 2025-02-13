package com.nagarpalika.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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

    private LocalDateTime uploadedAt= LocalDateTime.now();

}
