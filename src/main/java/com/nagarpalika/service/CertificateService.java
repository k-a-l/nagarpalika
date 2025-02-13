package com.nagarpalika.service;

import com.nagarpalika.dto.BirthCertificateDto;
import com.nagarpalika.dto.BirthCertificateRequestDto;

public interface CertificateService {
    BirthCertificateDto getBirthCertificate(Long id);
    BirthCertificateDto addBirthCertificate(BirthCertificateRequestDto request); //birthCertificateRequestDto);
    BirthCertificateDto updateBirthCertificate(Long id, BirthCertificateRequestDto birthCertificateRequestDto);
    void deleteBirthCertificate(Long id);

}
