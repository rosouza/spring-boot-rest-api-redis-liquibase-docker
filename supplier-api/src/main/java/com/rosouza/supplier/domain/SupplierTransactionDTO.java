package com.rosouza.supplier.domain;

import com.rosouza.supplier.domain.validator.MaxByteLength;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link SupplierTransaction} entity.
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
    @MaxByteLength(64000)
    private String content;

}
