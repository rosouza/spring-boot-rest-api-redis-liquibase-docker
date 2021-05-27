package com.rosouza.supplier.service.mapper;

import com.rosouza.supplier.domain.Supplier;
import com.rosouza.supplier.domain.SupplierTransaction;
import com.rosouza.supplier.service.dto.SupplierDTO;
import com.rosouza.supplier.service.dto.SupplierTransactionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link SupplierTransaction} and its DTO {@link SupplierTransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SupplierTransactionMapper extends EntityMapper<SupplierTransactionDTO, SupplierTransaction> {

    default SupplierTransaction fromId(String id) {
        if (id == null) {
            return null;
        }
        var entity = new SupplierTransaction();
        entity.setId(id);
        return entity;
    }

}
