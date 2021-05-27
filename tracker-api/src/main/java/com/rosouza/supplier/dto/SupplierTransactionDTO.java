package com.rosouza.supplier.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SupplierTransactionDTO {

    private String id;

    private Long supplierId;

    private LocalDateTime dateTime;

    private String content;

}
