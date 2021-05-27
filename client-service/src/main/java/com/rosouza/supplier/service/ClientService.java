package com.rosouza.supplier.service;

import com.rosouza.supplier.service.dto.SupplierTransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientService {

    private final RestTemplate restTemplate;

    private static final String REST_URL = "http://supplier-api:8080/v1/supplier-transactions";


    //@Scheduled(cron = "* */1 * * * *")
    public void postTransactions() {

        var requestDto = new SupplierTransactionDTO();
        requestDto.setId(UUID.randomUUID().toString());
        requestDto.setSupplierId(1L);
        requestDto.setDateTime(LocalDateTime.now());
        requestDto.setContent(UUID.randomUUID().toString());

        log.debug("REST REQUEST to process a SupplierTransaction : {}", requestDto);

        var response = restTemplate.postForEntity(REST_URL, requestDto, SupplierTransactionDTO.class);

        log.debug("REST response status code : {}", response.getStatusCode());
        log.debug("REST response body : {}", response.getBody());

        var responseDto = response.getBody();

    }

}
