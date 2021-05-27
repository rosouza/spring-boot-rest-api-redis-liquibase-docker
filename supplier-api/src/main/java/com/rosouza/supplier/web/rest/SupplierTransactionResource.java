package com.rosouza.supplier.web.rest;

import com.rosouza.supplier.service.SupplierTransactionService;
import com.rosouza.supplier.service.dto.SupplierTransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class SupplierTransactionResource {

    private final SupplierTransactionService supplierTransactionService;

    /**
     * {@code POST  /v1/supplier-transactions} : Process a  supplier transaction.
     *
     * @param supplierTransactionDTO the supplierTransactionDTO to be processed.
     * @return the {@link ResponseEntity} with status {@code 202 (Accepted)},
     * or with status {@code 500 (Internal Server Error)} if supplier id does not exist.
     */
    @PostMapping("/v1/supplier-transactions")
    public ResponseEntity<SupplierTransactionDTO> processSupplierTransaction(
        @Valid @RequestBody SupplierTransactionDTO supplierTransactionDTO
    ) {
        log.debug("REST request to process a SupplierTransaction : {}", supplierTransactionDTO);
        var result = supplierTransactionService.processSupplierTransaction(supplierTransactionDTO);
        return ResponseEntity.accepted().body(result);
    }

}
