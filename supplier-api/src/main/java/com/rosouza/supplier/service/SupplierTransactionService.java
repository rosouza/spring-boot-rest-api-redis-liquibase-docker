package com.rosouza.supplier.service;

import com.rosouza.supplier.domain.Supplier;
import com.rosouza.supplier.domain.SupplierTransaction;
import com.rosouza.supplier.exception.SupplierAlreadyExistException;
import com.rosouza.supplier.exception.SupplierIdNotExistException;
import com.rosouza.supplier.exception.SupplierNotExistException;
import com.rosouza.supplier.exception.TrackServiceErrorException;
import com.rosouza.supplier.repository.SupplierRepository;
import com.rosouza.supplier.repository.SupplierTransactionRepository;
import com.rosouza.supplier.service.dto.SupplierDTO;
import com.rosouza.supplier.service.dto.SupplierTransactionDTO;
import com.rosouza.supplier.service.mapper.SupplierTransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class SupplierTransactionService {

    private final RestTemplate restTemplate;
    private final SupplierRepository supplierRepository;
    private final SupplierTransactionRepository supplierTransactionRepository;
    private final SupplierTransactionMapper supplierTransactionMapper;

    private static final String REST_URL = "http://tracker-api:8082/v1/track-transactions";

    /**
     * Process a Supplier Transaction.
     *
     * @param supplierTransactionDTO the entity to create.
     * @return the persisted entity.
     */
    @Transactional
    public SupplierTransactionDTO processSupplierTransaction(SupplierTransactionDTO supplierTransactionDTO) {

        //Checks if the transaction id was processed recently, otherwise a SupplierAlreadyExistException will be raise
        var cachedTransaction = supplierTransactionRepository
                .findById(supplierTransactionDTO.getId());

        if (cachedTransaction.isPresent()) {
            return supplierTransactionMapper.toDto(cachedTransaction.get());
        }

        validateSupplierId(supplierTransactionDTO);

        //Then persists the transaction in the Redis cache
        var newCachedTransaction = supplierTransactionRepository
                .save(supplierTransactionMapper.toEntity(supplierTransactionDTO));

        supplierTransactionDTO = supplierTransactionMapper.toDto(newCachedTransaction);

        trackTransaction(supplierTransactionDTO);

        return supplierTransactionDTO;
    }

    private void trackTransaction(SupplierTransactionDTO supplierTransactionDTO) {

        try {

            log.debug("REST REQUEST to track a SupplierTransaction : {}", supplierTransactionDTO);

            var response = restTemplate
                    .postForEntity(REST_URL, supplierTransactionDTO, SupplierTransactionDTO.class);

            log.debug("REST response status code : {}", response.getStatusCode());
            log.debug("REST response body : {}", response.getBody());

            if (!response.getStatusCode().equals(HttpStatus.ACCEPTED)) {
                throw new TrackServiceErrorException();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TrackServiceErrorException();
        }

    }

    private void validateSupplierId(SupplierTransactionDTO supplierTransactionDTO) {
        //Checks if given supplier id exists, otherwise will raise a SupplierNotExistException
        supplierRepository.findById(supplierTransactionDTO.getSupplierId())
                .orElseThrow(SupplierIdNotExistException::new);
    }

    private void throwAlreadyExist() {
        throw new SupplierAlreadyExistException();
    }
}
