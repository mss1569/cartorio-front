package com.mss1569.cartoriofront.service;

import com.mss1569.cartoriofront.domain.Certificate;
import com.mss1569.cartoriofront.domain.Notary;
import com.mss1569.cartoriofront.dto.CertificateDTO;
import com.mss1569.cartoriofront.dto.NotaryDTO;
import com.mss1569.cartoriofront.wrapper.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaryService {
    private static final String basePath = "https://mss1569-cartorio.herokuapp.com/api/v1";

    @Autowired
    private RestTemplate restTemplate;

    public Page<Notary> listAll(Pageable pageable) {
        return restTemplate.exchange(basePath + "/notaries", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Notary>>() {
                }, pageable).getBody();
    }

    public Notary save(NotaryDTO notaryDTO) {
        return restTemplate.postForEntity(basePath + "/notaries", notaryDTO, Notary.class).getBody();
    }

    public Notary findById(Long notaryId) {
        return restTemplate.getForEntity(basePath + "/notaries/{notaryId}", Notary.class, notaryId).getBody();
    }

    public Certificate findCertificateById(Long certificateId) {
        return restTemplate.getForEntity(basePath + "/certificates/{certificateId}", Certificate.class, certificateId).getBody();
    }

    public Page<Certificate> listAllCertificatesByNotary(Long notaryId,Pageable pageable) {
        return restTemplate.exchange(basePath + "/notaries/{notaryId}/certificates", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Certificate>>() {
                }, notaryId,pageable).getBody();
    }

    public Certificate saveCertificate(Long notaryId, CertificateDTO certificateDTO) {
        return restTemplate.postForEntity(basePath + "/notaries/{notaryId}/certificates", certificateDTO, Certificate.class, notaryId).getBody();
    }

    public Certificate updateCertificate(Long certificateId,CertificateDTO certificateDTO) {
        return restTemplate.exchange(basePath + "/certificates/{certificateId}",
                HttpMethod.PUT,
                new HttpEntity<>(certificateDTO),
                new ParameterizedTypeReference<Certificate>() {
                },
                certificateId).getBody();
    }

    public void deleteCertificate(Long certificateId) {
        restTemplate.exchange(basePath + "/certificates/{certificateId}",
                HttpMethod.DELETE, null,
                Void.class, certificateId);
    }

    public Notary update(Long notaryId,NotaryDTO notaryDTO) {
        return restTemplate.exchange(basePath + "/notaries/{notaryId}",
                HttpMethod.PUT,
                new HttpEntity<>(notaryDTO),
                new ParameterizedTypeReference<Notary>() {
                },
                notaryId).getBody();
    }

    public void delete(Long notaryId) {
        restTemplate.exchange(basePath + "/notaries/{notaryId}",
                HttpMethod.DELETE, null,
                Void.class, notaryId);
    }
}
