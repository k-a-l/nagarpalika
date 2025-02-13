package com.nagarpalika.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private Boolean isRead;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recepeint;
    private LocalDateTime sentAt= LocalDateTime.now();

}
