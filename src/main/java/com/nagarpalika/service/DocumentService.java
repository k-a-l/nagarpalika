package com.nagarpalika.service;

import com.nagarpalika.dto.DocumentDto;
import com.nagarpalika.dto.DocumentRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    // Get a single document by ID
    Optional<DocumentDto> getDocument(Long id);

    // Upload a new document (Single)
    DocumentDto addDocument(DocumentRequestDto requestDto, MultipartFile file);

    // Upload multiple documents at once
    List<DocumentDto> addMultipleDocuments(List<DocumentRequestDto> requestDtos, List<MultipartFile> files);

    // Update a document
    DocumentDto updateDocument(Long id, DocumentRequestDto requestDto);

    // Delete a document
    void deleteDocument(Long id);

    // Download an uploaded document
    byte[] downloadDocument(Long id);

    // Approve a document
    DocumentDto approveDocument(Long id);

    // Reject a document with a reason
    DocumentDto rejectDocument(Long id, String rejectionReason);
}