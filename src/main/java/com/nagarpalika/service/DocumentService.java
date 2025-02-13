package com.nagarpalika.service;

import com.nagarpalika.dto.DocumentDto;
import com.nagarpalika.dto.DocumentRequestDto;

public interface DocumentService {
    DocumentDto getDocument(Long id);
    DocumentDto addDocument(DocumentRequestDto requestDto); //birthCertificateRequestDto);
    DocumentDto updateDocument(Long id, DocumentRequestDto documentRequestDto);
    void deleteDocument(Long id);
}
