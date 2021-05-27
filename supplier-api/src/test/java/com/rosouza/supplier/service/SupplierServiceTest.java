package com.rosouza.supplier.service;

import com.rosouza.supplier.domain.Supplier;
import com.rosouza.supplier.exception.SupplierNotExistException;
import com.rosouza.supplier.repository.SupplierRepository;
import com.rosouza.supplier.repository.SupplierTransactionRepository;
import com.rosouza.supplier.service.dto.SupplierDTO;
import com.rosouza.supplier.service.mapper.SupplierMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static com.rosouza.supplier.SupplierTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(classes = {SupplierService.class, SupplierMapperImpl.class})
public class SupplierServiceTest {

    @Autowired
    SupplierService service;

    @MockBean
    SupplierRepository supplierRepository;

    @MockBean
    SupplierTransactionRepository supplierTransactionRepository;

    @Test
    void should_create_donor_id_if_data_is_valid() throws Exception {
        given(supplierRepository.save(any(Supplier.class))).willReturn(entity());
        SupplierDTO actualDTO = service.createSupplier(dto(true));

        assertThat(actualDTO).isNotNull();
        assertThat(actualDTO.getId()).isEqualTo(ENTITY_ID);
    }

    @Test
    void should_not_create_entity_with_id_throw_bad_request() {
        //The request does includes an entity ID which causes a "BadRequestException" to be thrown.
        assertThatExceptionOfType(new Exception("Bad Request").getClass()).isThrownBy(() -> service.createSupplier(dto(false)));
    }


    @Test
    void should_updated_entity_if_data_is_valid() throws Exception {

        given(supplierRepository.findById(any())).willReturn(Optional.of(entity()));
        given(supplierRepository.save(any(Supplier.class))).willReturn(entity());
        SupplierDTO actualDTO = service.updateSupplier(ENTITY_ID, dto(false));

        assertThat(actualDTO).isNotNull();
        assertThat(actualDTO.getId()).isEqualTo(ENTITY_ID);
    }

    @Test
    void should_not_update_when_id_is_null_throw_bad_request() {
        //The request does not include an entity ID which causes a "BadRequestException" to be thrown.
        assertThatExceptionOfType(new Exception("Bad Request").getClass()).isThrownBy(() -> service.updateSupplier(null, dto(true)));
    }

    @Test
    void should_not_updated_when_entity_id_can_not_be_found() {
        Optional<Supplier> emptyEntity = Optional.empty();
        given(supplierRepository.findById(ENTITY_NOT_FOUND_ID)).willReturn(emptyEntity);

        assertThatExceptionOfType(new SupplierNotExistException().getClass()).isThrownBy(() -> service.updateSupplier(ENTITY_NOT_FOUND_ID, dto(false)));
    }

    @Test
    void should_not_delete_when_id_is_null_throw_bad_request() {
        //The request does not include an entity ID which causes a "BadRequestException" to be thrown.
        assertThatExceptionOfType(new Exception("Bad Request").getClass()).isThrownBy(() -> service.deleteSupplier(null));
    }

    @Test
    void should_not_delete_when_entity_id_can_not_be_found() {
        Optional<Supplier> emptyEntity = Optional.empty();
        given(supplierRepository.findById(ENTITY_NOT_FOUND_ID)).willReturn(emptyEntity);

        assertThatExceptionOfType(new SupplierNotExistException().getClass()).isThrownBy(() -> service.deleteSupplier(ENTITY_NOT_FOUND_ID));
    }

    @Test
    void list_should_be_returned_if_there_is_data_in_DB() {
        var page = new PageImpl<>(List.of(entity()));
        given(supplierRepository.findAll(any(Pageable.class))).willReturn(page);

        var entities = supplierRepository.findAll(Pageable.unpaged());
        assertThat(entities).isNotNull();
        assertThat(entities.getSize()).isEqualTo(1);

        var entity = entities.iterator().next();
        assertThat(entity).isNotNull();
        assertThat(entity).hasFieldOrPropertyWithValue("id", ENTITY_ID);
        assertThat(entity).hasFieldOrPropertyWithValue("name", NAME);
        assertThat(entity).hasFieldOrPropertyWithValue("country", COUNTRY);
        assertThat(entity).hasFieldOrPropertyWithValue("city", CITY);
        assertThat(entity).hasFieldOrPropertyWithValue("address", ADDRESS);
        assertThat(entity).hasFieldOrPropertyWithValue("contactNumber", CONTACT_NUMBER);
    }

    @Test
    void should_return_if_entity_id_is_valid() {
        given(supplierRepository.findById(ENTITY_ID)).willReturn(Optional.of(entity()));
        SupplierDTO actualDto = service.getSupplier(ENTITY_ID);
        assertThat(actualDto).isNotNull();
    }

    @Test
    void should_not_return_if_entity_id_is_invalid() {
        Optional<Supplier> emptyDevice = Optional.empty();
        given(supplierRepository.findById(ENTITY_NOT_FOUND_ID)).willReturn(emptyDevice);

        assertThatExceptionOfType(new SupplierNotExistException().getClass()).isThrownBy(() -> service.getSupplier(ENTITY_NOT_FOUND_ID));
    }

    Supplier entity() {
        var entity = new Supplier();
        entity.setId(ENTITY_ID);
        entity.setName(NAME);
        entity.setCountry(COUNTRY);
        entity.setState(STATE);
        entity.setCity(CITY);
        entity.setAddress(ADDRESS);
        entity.setContactNumber(CONTACT_NUMBER);
        return entity;
    }

    SupplierDTO dto(boolean isCreate) {
        var dto = new SupplierDTO();
        if (!isCreate) {
            dto.setId(ENTITY_ID);
        }
        dto.setName(NAME);
        dto.setCountry(COUNTRY);
        dto.setState(STATE);
        dto.setCity(CITY);
        dto.setAddress(ADDRESS);
        dto.setContactNumber(CONTACT_NUMBER);
        return dto;
    }

}
