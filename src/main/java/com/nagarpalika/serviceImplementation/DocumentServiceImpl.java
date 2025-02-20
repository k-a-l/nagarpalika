package com.nagarpalika.serviceImplementation;

import com.nagarpalika.dto.DocumentDto;
import com.nagarpalika.dto.DocumentRequestDto;
import com.nagarpalika.mapper.DocumentMapper;
import com.nagarpalika.model.ApplicationStatus;
import com.nagarpalika.model.User;
import com.nagarpalika.repository.DocumentRepository;
import com.nagarpalika.repository.UserRepository;
import com.nagarpalika.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.nagarpalika.model.Document;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<DocumentDto> getDocument(Long id) {
        return documentRepository.findById(id).map(DocumentMapper::toDocumentDto);
    }

    @Override
    public DocumentDto addDocument(DocumentRequestDto requestDto, MultipartFile file) {
        User uploadedBy = userRepository.findById(requestDto.getUploadedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Document document = DocumentMapper.toDocument(requestDto, uploadedBy);
        try {
            document.setData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file data", e);
        }

        Document savedDocument = documentRepository.save(document);
        return DocumentMapper.toDocumentDto(savedDocument);
    }

    @Override
    public List<DocumentDto> addMultipleDocuments(List<DocumentRequestDto> requestDtos, List<MultipartFile> files) {
        if (requestDtos.size() != files.size()) {
            throw new RuntimeException("Number of request DTOs and files must match");
        }

        return requestDtos.stream()
                .map(requestDto -> {
                    MultipartFile file = files.get(requestDtos.indexOf(requestDto));
                    return addDocument(requestDto, file);
                })
                .collect(Collectors.toList());
    }

    @Override
    public DocumentDto updateDocument(Long id, DocumentRequestDto requestDto) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        User uploadedBy = userRepository.findById(requestDto.getUploadedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Document updatedDocument = DocumentMapper.toUpdatedDocument(document, requestDto, uploadedBy);
        Document savedDocument = documentRepository.save(updatedDocument);
        return DocumentMapper.toDocumentDto(savedDocument);
    }

    @Override
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public byte[] downloadDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return document.getData();
    }

    @Override
    public DocumentDto approveDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        document.setStatus(ApplicationStatus.APPROVED);
        document.setRejectionReason(null);

        Document savedDocument = documentRepository.save(document);
        return DocumentMapper.toDocumentDto(savedDocument);
    }

    @Override
    public DocumentDto rejectDocument(Long id, String rejectionReason) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        document.setStatus(ApplicationStatus.REJECTED);
        document.setRejectionReason(rejectionReason);

        Document savedDocument = documentRepository.save(document);
        return DocumentMapper.toDocumentDto(savedDocument);
    }
}