package com.nagarpalika.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.nagarpalika.model.Document; // Ensure this is the correct import

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Column(unique = false, nullable = false)
    private String email;

    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isActive;

    @OneToMany(mappedBy = "uploadedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> documents; // Ensure this uses the correct Document class
}