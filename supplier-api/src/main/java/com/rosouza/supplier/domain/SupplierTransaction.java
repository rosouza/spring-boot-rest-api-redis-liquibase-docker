package com.rosouza.supplier.domain;

import com.rosouza.supplier.domain.validator.MaxByteLength;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@RedisHash(value = "SupplierTransaction", timeToLive = 60)
public class SupplierTransaction {

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
