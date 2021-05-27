package com.rosouza.supplier;

import java.time.LocalDateTime;

public class SupplierTransactionTestConstants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";

    public static final String ENTITY_NOT_FOUND_ID = "entity.id-not-found.other";

    public static final String ID = "entity.id";
    public static final Long SUPPLIER_ID = 1L;
    public static final LocalDateTime DATE_TIME = LocalDateTime.now();
    public static final String CONTENT = "entity.content.label";

    public static final String ID_OTHER = "entity.id.other";
    public static final Long SUPPLIER_ID_OTHER = 1L;
    public static final LocalDateTime DATE_TIME_OTHER = LocalDateTime.now().plusDays(1);
    public static final String CONTENT_OTHER = "entity.content-other.label";

}
