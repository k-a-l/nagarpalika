package com.nagarpalika.controller;

import com.nagarpalika.dto.DocumentDto;
import com.nagarpalika.dto.DocumentRequestDto;
import com.nagarpalika.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // Get a single document by ID
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable Long id) {
        return documentService.getDocument(id)
                .map(ResponseEntity::ok) // If found, return 200 with the document
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return 404
    }

    // Upload a single document
    @PostMapping("/add")
    public ResponseEntity<DocumentDto> addDocument(
            @RequestPart DocumentRequestDto requestDto,
            @RequestPart MultipartFile file) {
        return ResponseEntity.ok(documentService.addDocument(requestDto, file));
    }

    // Upload multiple documents
    @PostMapping("/multiple")
    public ResponseEntity<List<DocumentDto>> addMultipleDocuments(
            @RequestPart List<DocumentRequestDto> requestDtos,
            @RequestPart List<MultipartFile> files) {
        return ResponseEntity.ok(documentService.addMultipleDocuments(requestDtos, files));
    }

    // Update a document
    @PutMapping("/{id}")
    public ResponseEntity<DocumentDto> updateDocument(
            @PathVariable Long id,
            @RequestBody DocumentRequestDto requestDto) {
        return ResponseEntity.ok(documentService.updateDocument(id, requestDto));
    }

    // Delete a document
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    // Download a document
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.downloadDocument(id));
    }

    // Approve a document
    @PostMapping("/{id}/approve")
    public ResponseEntity<DocumentDto> approveDocument(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.approveDocument(id));
    }

    // Reject a document
    @PostMapping("/{id}/reject")
    public ResponseEntity<DocumentDto> rejectDocument(
            @PathVariable Long id,
            @RequestParam String rejectionReason) {
        return ResponseEntity.ok(documentService.rejectDocument(id, rejectionReason));
    }
}