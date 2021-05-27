package com.rosouza.supplier.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosouza.supplier.exception.SupplierNotExistException;
import com.rosouza.supplier.service.SupplierService;
import com.rosouza.supplier.service.dto.SupplierDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.ZonedDateTime;

import static com.rosouza.supplier.SupplierTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupplierResource.class)
public class SupplierResourceTest {


    @MockBean
    SupplierService supplierService;

    @Autowired
    MockMvc mockMvc;

    SupplierDTO supplierDTO;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void before() {
        supplierDTO = supplierDTO(false);
        given(supplierService.getSupplier(eq(ENTITY_ID))).willReturn(supplierDTO);
    }

    @Test
    void supplierCanBeReadIfSupplierIdCanBeFound() throws Exception {
        MvcResult result = mockMvc.perform(get(GET_BY_ID, ENTITY_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(supplierDTO);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void supplierCanNotBeReadIfSupplierIdCanNotBeFound() throws Exception {
        given(supplierService.getSupplier(anyLong())).willThrow(SupplierNotExistException.class);

        mockMvc.perform(get(GET_BY_ID, ENTITY_NOT_FOUND_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void supplierCanNotBeCreatedIfSupplierIdAlreadyExists() throws Exception {
        //Request
        SupplierDTO supplierRequestDTO = supplierDTO(false);

        mockMvc.perform(post(CREATE_URI)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequestDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());

    }

    @Test
    void supplierCanBeCreatedWithCompleteRequest() throws Exception {
        //Request
        SupplierDTO supplierRequestDTO = supplierDTO(true);

        //Expected Response
        SupplierDTO supplierResponseDTO = supplierDTO(false);
        given(supplierService.createSupplier(any())).willReturn(supplierResponseDTO);

        MvcResult result = mockMvc.perform(post(CREATE_URI)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequestDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(supplierResponseDTO);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void supplierCanNotBeUpdatedWithBadRequest() throws Exception {
        //Request
        SupplierDTO supplierRequestDTO = supplierDTO(false);

        //Expected Response
        given(supplierService.updateSupplier(any(), any())).willThrow(new SupplierNotExistException());

        mockMvc.perform(put(UPDATE_URI, ENTITY_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequestDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect((status().isNotFound()));
    }

    @Test
    void supplierCanNotBeUpdatedWithIfSupplierIdCanNotBeFound() throws Exception {
        //Request
        SupplierDTO supplierRequestDTO = supplierDTO(false);

        //Expected Response
        given(supplierService.updateSupplier(any(), any())).willThrow(new SupplierNotExistException());

        mockMvc.perform(put(UPDATE_URI, ENTITY_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequestDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void supplierCanBeUpdatedIfSupplierIdCanBeFound() throws Exception {
        //Request
        SupplierDTO supplierRequestDTO = supplierDTO(false);

        //Expected Response
        SupplierDTO supplierResponseDTO = supplierDTO(false);
        supplierRequestDTO.setId(ENTITY_ID);
        given(supplierService.updateSupplier(any(), any())).willReturn(supplierResponseDTO);

        MvcResult result = mockMvc.perform(put(UPDATE_URI, ENTITY_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequestDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(supplierResponseDTO);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void supplierCanBeUpdatedWithSupplierUpdatePermission() throws Exception {
        //Request
        SupplierDTO supplierRequestDTO = supplierDTO(false);

        //Expected Response
        SupplierDTO supplierResponseDTO = supplierDTO(false);
        supplierRequestDTO.setId(ENTITY_ID);
        given(supplierService.updateSupplier(eq(ENTITY_ID), any())).willReturn(supplierResponseDTO);

        MvcResult result = mockMvc.perform(put(UPDATE_URI, ENTITY_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequestDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(supplierResponseDTO);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void supplierCanBeCreatedWithSupplierCreatePermission() throws Exception {
        //Request
        SupplierDTO supplierRequestDTO = supplierDTO(true);

        //Expected Response
        SupplierDTO supplierResponseDTO = supplierDTO(false);
        given(supplierService.createSupplier(any())).willReturn(supplierResponseDTO);

        MvcResult result = mockMvc.perform(post(CREATE_URI)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequestDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(supplierResponseDTO);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    private SupplierDTO supplierDTO(Boolean isCreate) {
        var supplierDTO = new SupplierDTO();

        if (!isCreate){
            supplierDTO.setId(ENTITY_ID);
        }

        supplierDTO.setName(NAME);
        supplierDTO.setCountry(COUNTRY);
        supplierDTO.setState(STATE);
        supplierDTO.setCity(CITY);
        supplierDTO.setAddress(ADDRESS);
        supplierDTO.setContactNumber(CONTACT_NUMBER);
        return supplierDTO;
    }
}
