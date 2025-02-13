package com.nagarpalika.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BirthCertificateRequestDto {
    @NotBlank(message = "Child name is required")
    @Size(min = 3, max = 50, message = "Child name must be between 3 and 50 characters")
    private String childName;
    @NotBlank(message = "Father name is required")
    @Size(min = 3, max = 50, message = "Father name must be between 3 and 50 characters")
    private String fatherName;
    @NotBlank(message = "Mother name is required")
    @Size(min = 3, max = 50, message = "Mother name must be between 3 and 50 characters")

    private String motherName;
    @NotBlank(message = "Date of birth is required")
    @PastOrPresent(message = "Date of birth must be in the past or present")
    private String dateOfBirth;
    @NotBlank(message = "Place of birth is required")
    @Size(min = 3, max = 50, message = "Place of birth must be between 3 and 50 characters")
    private String placeOfBirth;
    @NotBlank(message = "Place of birth must be between 3 and 50 characters")
    private String gender;
    private Long applicantId;


}
