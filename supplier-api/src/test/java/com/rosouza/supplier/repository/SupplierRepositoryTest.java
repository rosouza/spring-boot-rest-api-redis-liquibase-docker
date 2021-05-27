package com.rosouza.supplier.repository;

import com.rosouza.supplier.config.LiquibaseConfiguration;
import com.rosouza.supplier.domain.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


import static com.rosouza.supplier.SupplierTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(SPRING_PROFILE_TEST)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableConfigurationProperties({LiquibaseProperties.class})
@TestPropertySource(properties = {
"spring.jpa.hibernate.ddl-auto=validate"
})
@Import({LiquibaseConfiguration.class})
class SupplierRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    SupplierRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.flush();
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void should_delete_all_supplier() {
        entityManager.persist(entity());
        entityManager.persist(entityOther());

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void should_delete_supplier_by_id() {
        var entity1 = entity();
        entityManager.persist(entity1);

        var entity2 = entity();
        entityManager.persist(entity2);

        var entity3 = entityOther();
        entityManager.persist(entity3);

        repository.deleteById(entity2.getId());

        var donations = repository.findAll();
        assertThat(donations).hasSize(2).contains(entity1, entity3);
    }

    @Test
    void should_find_all_supplier() {
        var entity1 = entity();
        entityManager.persist(entity1);

        var entity2 = entity();
        entityManager.persist(entity2);

        var entity3 = entityOther();
        entityManager.persist(entity3);

        var donations = repository.findAll();
        assertThat(donations).hasSize(3).contains(entity1, entity2, entity3);
    }

    @Test
    void should_find_supplier_by_id() {
        var entity1 = entity();
        entityManager.persist(entity1);

        var entity2 = entity();
        entityManager.persist(entity2);

        var foundDonor = repository.findById(entity1.getId()).get();
        assertThat(foundDonor).isEqualTo(entity1);

        foundDonor = repository.findById(entity2.getId()).get();
        assertThat(foundDonor).isEqualTo(entity2);
    }

    @Test
    void should_find_no_supplier_if_repository_is_empty() {
        var donations = repository.findAll();
        assertThat(donations).isEmpty();
    }

    @Test
    void should_store_a_supplier() {
        var entity = repository.save(entity());
        assertThat(entity).hasFieldOrPropertyWithValue("name", NAME);
        assertThat(entity).hasFieldOrPropertyWithValue("country", COUNTRY);
        assertThat(entity).hasFieldOrPropertyWithValue("city", CITY);
        assertThat(entity).hasFieldOrPropertyWithValue("address", ADDRESS);
        assertThat(entity).hasFieldOrPropertyWithValue("contactNumber", CONTACT_NUMBER);

        var entityOther = repository.save(entityOther());
        assertThat(entityOther).hasFieldOrPropertyWithValue("name", NAME_OTHER);
        assertThat(entityOther).hasFieldOrPropertyWithValue("country", COUNTRY_OTHER);
        assertThat(entityOther).hasFieldOrPropertyWithValue("city", CITY_OTHER);
        assertThat(entityOther).hasFieldOrPropertyWithValue("address", ADDRESS_OTHER);
        assertThat(entityOther).hasFieldOrPropertyWithValue("contactNumber", CONTACT_NUMBER_OTHER);
    }

    @Test
    void should_update_supplier_by_id() {
        var entity1 = entity();
        entityManager.persist(entity1);

        var entity2 = entity();
        entityManager.persist(entity2);

        var entity3 = entityOther();
        entityManager.persist(entity3);

        var entity = repository.findById(entity2.getId()).get();
        entity.setName(entity3.getName());
        entity.setCountry(entity3.getCountry());
        entity.setCity(entity3.getCity());
        entity.setAddress(entity3.getAddress());
        entity.setContactNumber(entity3.getContactNumber());
        repository.save(entity);

        var checkEntity = repository.findById(entity2.getId()).get();
        assertThat(checkEntity.getId()).isEqualTo(entity2.getId());
        assertThat(checkEntity.getName()).isEqualTo(entity3.getName());
        assertThat(checkEntity.getCountry()).isEqualTo(entity3.getCountry());
        assertThat(checkEntity.getCity()).isEqualTo(entity3.getCity());
        assertThat(checkEntity.getAddress()).isEqualTo(entity3.getAddress());
        assertThat(checkEntity.getContactNumber()).isEqualTo(entity3.getContactNumber());
    }

    Supplier entity() {
        var entity = new Supplier();
        entity.setName(NAME);
        entity.setCountry(COUNTRY);
        entity.setState(STATE);
        entity.setCity(CITY);
        entity.setAddress(ADDRESS);
        entity.setContactNumber(CONTACT_NUMBER);
        return entity;
    }

    Supplier entityOther() {
        var entity = new Supplier();
        entity.setName(NAME_OTHER);
        entity.setCountry(COUNTRY_OTHER);
        entity.setState(STATE_OTHER);
        entity.setCity(CITY_OTHER);
        entity.setAddress(ADDRESS_OTHER);
        entity.setContactNumber(CONTACT_NUMBER_OTHER);
        return entity;
    }

}
