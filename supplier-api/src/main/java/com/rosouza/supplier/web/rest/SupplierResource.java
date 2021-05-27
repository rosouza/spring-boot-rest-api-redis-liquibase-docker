package com.rosouza.supplier.web.rest;

import com.rosouza.supplier.service.SupplierService;
import com.rosouza.supplier.service.dto.SupplierDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class SupplierResource {

    private final SupplierService supplierService;

    /**
     * {@code POST  /v1/suppliers} : Create a new supplier.
     *
     * @param supplierDTO the supplierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supplierDTO,
     * or with status {@code 400 (Bad Request)} if the supplier has already an ID.
     */
    @PostMapping("/v1/suppliers")
    public ResponseEntity<SupplierDTO> createSupplier(
        @Valid @RequestBody SupplierDTO supplierDTO
    ) {
        log.debug("REST request to save Supplier : {}", supplierDTO);

        if (Objects.nonNull(supplierDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }

        var result = supplierService.createSupplier(supplierDTO);
        return ResponseEntity.created(URI.create("/v1/suppliers/" + result.getId())).body(result);
    }

    /**
     * {@code DELETE  /v1/suppliers/:id} : delete the "id" supplier.
     *
     * @param id        the id of the Supplier to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/v1/suppliers/{id}")
    public ResponseEntity<Void> deleteSupplier(
        @PathVariable Long id
    ) {
        log.debug("REST request to delete a Supplier: {}", id);
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code GET  /v1/suppliers/:id} : get the "id" supplier.
     *
     * @param id the id of the supplier to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)},
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/v1/suppliers/{id}")
    public ResponseEntity<SupplierDTO> getSupplier(
        @PathVariable Long id
    ) {
        log.debug("REST request to get a Supplier: {}", id);
        var result = supplierService.getSupplier(id);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code PUT  /v1/suppliers/:id} : Updates an existing supplier.
     *
     * @param id             the id of the supplier to update.
     * @param supplierDTO the supplierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplierDTO,
     * or with status {@code 400 (Bad Request)} if the supplierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supplierDTO couldn't be updated.
     */
    @PutMapping("/v1/suppliers/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(
        @PathVariable Long id,
        @Valid @RequestBody SupplierDTO supplierDTO
    ) {
        log.debug("REST request to update a Supplier: {} {}", id, supplierDTO);
        var result = supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok().body(result);
    }

}
