package com.globits.da.service.impl;

import com.globits.da.domain.Certificate;
import com.globits.da.repository.CertificateRepo;
import com.globits.da.service.CertificateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CertificateImp implements CertificateService {
    CertificateRepo certificateRepo;
    @Transactional
    @Override
    public List<Certificate> findAllCertificate(){
        return certificateRepo.findAll();
    }
    @Transactional
    @Override
    public void addListCertificate(List<Certificate> certificates){
        try{
            certificateRepo.saveAll(certificates);
            System.out.println("Add successed!!!");
        }catch (Exception e){
            e.getMessage();
            System.out.println("Add failed!!!");
        }
    }
    @Transactional
    @Override
    public void updateCertificateById(Certificate certificate){
        try{
            Certificate certificate1 = certificateRepo.getOne(certificate.getId());
            if(certificate1 != null){
                certificate1 = Certificate.builder()
                        .id(certificate.getId())
                        .name(certificate.getName())
                        .startDate(certificate.getStartDate())
                        .endDate(certificate.getEndDate())
                        .degree(certificate.getDegree())
                        .build();
                certificateRepo.save(certificate1);
                System.out.println("Update certificate id = " + certificate.getId()+" success!!!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
    @Transactional
    @Override
    public void deleteCertificateById(int id){
        try{
            if(certificateRepo.getOne(id) != null){
                certificateRepo.deleteById(id);
                System.out.println("Delete certificate id = " + id +" success!!!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}
