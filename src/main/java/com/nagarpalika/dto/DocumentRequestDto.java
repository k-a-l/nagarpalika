package com.nagarpalika.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequestDto {
    private String documentName;
    private String documentType;
    private Long uploadedBy;
}