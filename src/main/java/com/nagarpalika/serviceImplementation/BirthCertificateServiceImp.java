package com.nagarpalika.serviceImplementation;

import com.nagarpalika.dto.BirthCertificateDto;
import com.nagarpalika.dto.BirthCertificateRequestDto;
import com.nagarpalika.mapper.BirthCertificateMapper;
import com.nagarpalika.model.ApplicationStatus;
import com.nagarpalika.model.BirthCertificate;
import com.nagarpalika.model.User;
import com.nagarpalika.repository.BirthCertificateRepository;
import com.nagarpalika.repository.UserRepository;
import com.nagarpalika.service.BirthCertificateService;
//import com.nagarpalika.utils.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BirthCertificateServiceImp implements BirthCertificateService {

    @Autowired
    private BirthCertificateRepository birthCertificateRepository;

    @Autowired
    private UserRepository userRepository; // Needed to fetch applicant details

    @Override
    public BirthCertificateDto addBirthCertificate(BirthCertificateRequestDto request) {
        User applicant = userRepository.findById(request.getApplicantId())
                .orElseThrow(() -> new IllegalArgumentException("Applicant not found"));

        BirthCertificate birthCertificate = BirthCertificateMapper.fromRequestDto(request, applicant);
        birthCertificate = birthCertificateRepository.save(birthCertificate);

        return BirthCertificateMapper.toBirthCertificateDto(birthCertificate);
    }

    @Override
    public BirthCertificateDto getBirthCertificate(Long id) {
        return birthCertificateRepository.findById(id)
                .map(BirthCertificateMapper::toBirthCertificateDto)
                .orElseThrow(() -> new IllegalArgumentException("Birth Certificate not found"));
    }

    @Override
    public List<BirthCertificateDto> getAllBirthCertificates(String status, String applicantName) {
        List<BirthCertificate> birthCertificates;

        if (status != null && applicantName != null) {
            birthCertificates = birthCertificateRepository.findByStatusAndApplicantUsername(
                    ApplicationStatus.valueOf(status), applicantName);
        } else if (status != null) {
            birthCertificates = birthCertificateRepository.findByStatus(ApplicationStatus.valueOf(status));
        } else if (applicantName != null) {
            birthCertificates = birthCertificateRepository.findByApplicantUsername(applicantName);
        } else {
            birthCertificates = birthCertificateRepository.findAll();
        }

        return birthCertificates.stream()
                .map(BirthCertificateMapper::toBirthCertificateDto)
                .collect(Collectors.toList());
    }

    @Override
    public BirthCertificateDto updateBirthCertificate(Long id, BirthCertificateRequestDto request) {
        BirthCertificate birthCertificate = birthCertificateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Birth Certificate not found"));

        BirthCertificateMapper.updateFromRequestDto(birthCertificate, request);
        birthCertificate = birthCertificateRepository.save(birthCertificate);

        return BirthCertificateMapper.toBirthCertificateDto(birthCertificate);
    }

    @Override
    public void deleteBirthCertificate(Long id) {
        if (!birthCertificateRepository.existsById(id)) {
            throw new IllegalArgumentException("Birth Certificate not found");
        }
        birthCertificateRepository.deleteById(id);
    }

    @Override
    public BirthCertificateDto approveBirthCertificate(Long id) {
        BirthCertificate birthCertificate = birthCertificateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Birth Certificate not found"));

        birthCertificate.setStatus(ApplicationStatus.APPROVED);
        birthCertificate.setRejectionReason(null); // Clear rejection reason if previously rejected
        birthCertificate = birthCertificateRepository.save(birthCertificate);

        return BirthCertificateMapper.toBirthCertificateDto(birthCertificate);
    }

    @Override
    public BirthCertificateDto rejectBirthCertificate(Long id, String rejectionReason) {
        BirthCertificate birthCertificate = birthCertificateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Birth Certificate not found"));

        birthCertificate.setStatus(ApplicationStatus.REJECTED);
        birthCertificate.setRejectionReason(rejectionReason);
        birthCertificate = birthCertificateRepository.save(birthCertificate);

        return BirthCertificateMapper.toBirthCertificateDto(birthCertificate);
    }

    @Override
    public byte[] generateBirthCertificatePDF(Long id) {
        BirthCertificate birthCertificate = birthCertificateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Birth Certificate not found"));
    return null;
        //return PDFGenerator.generateBirthCertificatePDF(birthCertificate);
    }
}
