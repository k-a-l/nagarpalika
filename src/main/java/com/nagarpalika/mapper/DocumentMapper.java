package com.nagarpalika.mapper;

import com.nagarpalika.dto.DocumentDto;
import com.nagarpalika.dto.DocumentRequestDto;
import com.nagarpalika.model.Document;
import com.nagarpalika.model.User;
import com.nagarpalika.model.ApplicationStatus;

import java.time.LocalDateTime;

public class DocumentMapper {

    // Convert Document entity to DocumentDto
    public static DocumentDto toDocumentDto(Document document) {
        return new DocumentDto(
                document.getId(),
                document.getDocumentName(),
                document.getDocumentType(),
                document.getUploadedBy() != null ? document.getUploadedBy().getId() : null,
                document.getUploadedAt(),
                document.getDocumentUrl(),
                document.getStatus(),
                document.getRejectionReason()
        );
    }

    // Convert DocumentRequestDto to Document entity
    public static Document toDocument(DocumentRequestDto requestDto, User uploadedBy) {
        Document document = new Document();
        document.setDocumentName(requestDto.getDocumentName());
        document.setDocumentType(requestDto.getDocumentType());
        document.setUploadedBy(uploadedBy);
        document.setStatus(ApplicationStatus.PENDING); // Default status
        return document;
    }

    // Update Document entity with DocumentRequestDto
    public static Document toUpdatedDocument(Document document, DocumentRequestDto requestDto, User uploadedBy) {
        document.setDocumentName(requestDto.getDocumentName());
        document.setDocumentType(requestDto.getDocumentType());
        document.setUploadedBy(uploadedBy);
        return document;
    }
}