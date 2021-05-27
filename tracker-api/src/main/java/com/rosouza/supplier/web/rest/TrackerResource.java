package com.rosouza.supplier.web.rest;

import com.rosouza.supplier.dto.SupplierTransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class TrackerResource {

    @PostMapping("/v1/track-transactions")
    public ResponseEntity<SupplierTransactionDTO> trackTransaction(
        @RequestBody SupplierTransactionDTO supplierTransactionDTO
    ) {
        log.debug("REST request to track a SupplierTransaction : {}", supplierTransactionDTO);
        return ResponseEntity.accepted().body(supplierTransactionDTO);
    }

}
