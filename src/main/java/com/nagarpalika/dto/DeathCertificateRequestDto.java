package com.nagarpalika.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeathCertificateRequestDto {
    private String deceasedName;
    private String fatherName;
    private String motherName;
    private String dateOfBirth;
    private String placeOfBirth;
    private String causeOfDeath;
    private Long applicantId;
}
