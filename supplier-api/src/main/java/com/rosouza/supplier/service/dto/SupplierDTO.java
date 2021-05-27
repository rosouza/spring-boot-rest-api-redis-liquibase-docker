package com.rosouza.supplier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * A DTO for the {@link com.rosouza.supplier.domain.Supplier} entity.
 */
@Data
@NoArgsConstructor
public class SupplierDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String city;

    @NotNull
    @Size(max = 100)
    private String country;

    @NotNull
    @Size(max = 100)
    private String state;

    @NotNull
    @Size(max = 100)
    private String address;

    @Size(max = 14)
    private String contactNumber;

}
