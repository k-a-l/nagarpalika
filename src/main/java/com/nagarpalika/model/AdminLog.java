package com.nagarpalika.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_logs")
public class AdminLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User adminUser;
    private LocalDateTime timeStamp= LocalDateTime.now();

}
