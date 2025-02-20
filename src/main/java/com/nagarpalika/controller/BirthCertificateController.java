package com.nagarpalika.controller;

import com.nagarpalika.dto.BirthCertificateDto;
import com.nagarpalika.dto.BirthCertificateRequestDto;
import com.nagarpalika.service.BirthCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/birth-certificate")
public class BirthCertificateController {

    @Autowired
  private BirthCertificateService birthCertificateService;

    @GetMapping("/all")
    public ResponseEntity<List<BirthCertificateDto>> findAllBirthCertificates(){
        List<BirthCertificateDto> birthCertificateDto=birthCertificateService.getAllBirthCertificates();
        return ResponseEntity.status(200).body(birthCertificateDto);
    }

    @PostMapping("/register")
    public ResponseEntity<BirthCertificateDto> addBirthCertificate(@RequestBody BirthCertificateRequestDto request) {
        BirthCertificateDto createBirthCertificate=birthCertificateService.addBirthCertificate(request);
        return ResponseEntity.status(201).body(createBirthCertificate);
    }

   @GetMapping("/{id}")
   public ResponseEntity<BirthCertificateDto> findAllBirthCertificatesById(@PathVariable Long id){
    BirthCertificateDto birthCertificateDto=birthCertificateService.getBirthCertificate(id);
    return ResponseEntity.status(200).body(birthCertificateDto);
   }

   @GetMapping("/all-status-applicant")
    public ResponseEntity<List<BirthCertificateDto>> findByStatusApplicantName(@RequestParam(required = false) String status, @RequestParam(required = false) String applicantName){
        List<BirthCertificateDto> birthCertificateDtoList=birthCertificateService.getAllBirthCertificates(status,applicantName);
        return ResponseEntity.status(200).body(birthCertificateDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirthCertificateDto> updateBirthCertificate(@PathVariable Long id, @RequestBody BirthCertificateRequestDto request){
        BirthCertificateDto updateBirthCertificate=birthCertificateService.updateBirthCertificate(id,request);
        return ResponseEntity.status(200).body(updateBirthCertificate);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirthCertificate(@PathVariable Long id){
        birthCertificateService.deleteBirthCertificate(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<BirthCertificateDto> approveBirthCertificate(@PathVariable Long id){
        BirthCertificateDto approveBirthCertificate=birthCertificateService.approveBirthCertificate(id);
        return ResponseEntity.status(200).body(approveBirthCertificate);
    }
    @PutMapping("/reject/{id}")
    public ResponseEntity<BirthCertificateDto> rejectBirthCertificate(@PathVariable Long id, @RequestParam String rejectionReason){
        BirthCertificateDto rejectBirthCertificate=birthCertificateService.rejectBirthCertificate(id,rejectionReason);
        return ResponseEntity.status(200).body(rejectBirthCertificate);
    }
    @GetMapping("/generate-pdf/{id}")
    public ResponseEntity<byte[]> generateBirthCertificatePDF(@PathVariable Long id){
        byte[] pdf=birthCertificateService.generateBirthCertificatePDF(id);
        return ResponseEntity.status(200).body(pdf);
    }

}
