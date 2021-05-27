package com.rosouza.supplier;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public class SupplierTestConstants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";

    public static final Long ENTITY_ID = 1L;
    public static final Long ENTITY_NOT_FOUND_ID = 99L;

    public static final String NAME = "entity.name.label";
    public static final String COUNTRY = "entity.country.label";
    public static final String STATE = "entity.state.label";
    public static final String CITY = "entity.city.label";
    public static final String ADDRESS = "entity.address.label";
    public static final String CONTACT_NUMBER = "1111111111";

    public static final String NAME_OTHER = "entity.name-other.label";
    public static final String COUNTRY_OTHER = "entity.country-other.label";
    public static final String STATE_OTHER = "entity.state-other.label";
    public static final String CITY_OTHER = "entity.city-other.label";
    public static final String ADDRESS_OTHER = "entity.address-other.label";
    public static final String CONTACT_NUMBER_OTHER = "222222222222";

    public static final String GET_ALL = "/v1/suppliers";
    public static final String GET_BY_ID = "/v1/suppliers/{id}";
    public static final String CREATE_URI = "/v1/suppliers";
    public static final String UPDATE_URI = "/v1/suppliers/{id}";

}
