package com.globits.da.service;

import com.globits.da.domain.Certificate;

import java.util.List;

public interface CertificateService {
    List<Certificate> findAllCertificate();
    void addListCertificate(List<Certificate> certificates);
    void updateCertificateById(Certificate certificate);
    void deleteCertificateById(int id);
}
