package com.rosouza.supplier.service.mapper;

import com.rosouza.supplier.domain.Supplier;
import com.rosouza.supplier.service.dto.SupplierDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Supplier} and its DTO {@link SupplierDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SupplierMapper extends EntityMapper<SupplierDTO, Supplier> {

    default Supplier fromId(Long id) {
        if (id == null) {
            return null;
        }
        var entity = new Supplier();
        entity.setId(id);
        return entity;
    }

}
