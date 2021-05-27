package com.rosouza.supplier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.rosouza.supplier.domain.SupplierTransaction} entity.
 */
@Data
@NoArgsConstructor
public class SupplierTransactionDTO {

    @NotNull
    private String id;

    @NotNull
    private Long supplierId;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String content;

}
