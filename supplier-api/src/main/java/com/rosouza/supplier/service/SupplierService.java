package com.rosouza.supplier.service;

import com.rosouza.supplier.exception.SupplierAlreadyExistException;
import com.rosouza.supplier.exception.SupplierNotExistException;
import com.rosouza.supplier.repository.SupplierRepository;
import com.rosouza.supplier.service.dto.SupplierDTO;
import com.rosouza.supplier.service.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SupplierService {

    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    /**
     * Create a Supplier.
     *
     * @param supplierDTO the entity to create.
     * @return the persisted entity.
     */
    @Transactional
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {

        if (Objects.nonNull(supplierDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be null");
        }

        var result = supplierRepository.save(supplierMapper.toEntity(supplierDTO));
        return supplierMapper.toDto(result);
    }

    /**
     * Deletes a Supplier.
     *
     * @param id             the id of the entity.
     */
    @Transactional
    public void deleteSupplier(Long id) {
        var supplier = supplierRepository.findById(id).orElseThrow(SupplierNotExistException::new);
        supplierRepository.delete(supplier);
    }

    /**
     * Get one Supplier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public SupplierDTO getSupplier(Long id) {
        var result = supplierRepository.findById(id).orElseThrow(SupplierNotExistException::new);
        return supplierMapper.toDto(result);
    }

    /**
     * Update Supplier by id.
     *
     * @param id             the id of the entity.
     * @param supplierDTO the entity to update.
     * @return the entity.
     */
    @Transactional
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        var supplier = supplierRepository.findById(id).orElseThrow(SupplierNotExistException::new);
        supplier = supplierMapper.toEntity(supplierDTO);
        var result = supplierRepository.save(supplier);
        return supplierMapper.toDto(result);
    }

    private void throwAlreadyExist() {
        throw new SupplierAlreadyExistException();
    }
}
