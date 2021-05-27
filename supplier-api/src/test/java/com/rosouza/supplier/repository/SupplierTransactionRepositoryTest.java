package com.rosouza.supplier.repository;

import com.rosouza.supplier.config.EmbeddedRedisConfiguration;
import com.rosouza.supplier.domain.SupplierTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static com.rosouza.supplier.SupplierTestConstants.SPRING_PROFILE_TEST;
import static com.rosouza.supplier.SupplierTransactionTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(SPRING_PROFILE_TEST)
@DataRedisTest
@Import({EmbeddedRedisConfiguration.class})
class SupplierTransactionRepositoryTest {

    @Autowired
    SupplierTransactionRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void should_delete_all_supplier_transaction() {
        repository.save(entity());
        repository.save(entityOther());

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void should_delete_supplier_transaction_by_id() {
        var entity1 = entity();
        repository.save(entity1);

        var entity2 = entityOther();
        repository.save(entity2);

        repository.deleteById(entity2.getId());

        var donations = repository.findAll();
        assertThat(donations).hasSize(1).contains(entity1);
    }

    @Test
    void should_find_all_supplier_transaction() {
        var entity1 = entity();
        repository.save(entity1);

        var entity2 = entityOther();
        repository.save(entity2);

        var donations = repository.findAll();
        assertThat(donations).hasSize(2).contains(entity1, entity2);
    }

    @Test
    void should_find_supplier_transaction_by_id() {
        var entity1 = entity();
        repository.save(entity1);

        var entity2 = entity();
        repository.save(entity2);

        var foundDonor = repository.findById(entity1.getId()).get();
        assertThat(foundDonor).isEqualTo(entity1);

        foundDonor = repository.findById(entity2.getId()).get();
        assertThat(foundDonor).isEqualTo(entity2);
    }

    @Test
    void should_find_no_supplier_transaction_if_repository_is_empty() {
        var donations = repository.findAll();
        assertThat(donations).isEmpty();
    }

    @Test
    void should_store_a_supplier_transaction() {
        var entity = repository.save(entity());
        assertThat(entity).hasFieldOrPropertyWithValue("id", ID);
        assertThat(entity).hasFieldOrPropertyWithValue("supplierId", SUPPLIER_ID);
        assertThat(entity).hasFieldOrPropertyWithValue("dateTime", DATE_TIME);
        assertThat(entity).hasFieldOrPropertyWithValue("content", CONTENT);

        var entityOther = repository.save(entityOther());
        assertThat(entityOther).hasFieldOrPropertyWithValue("id", ID_OTHER);
        assertThat(entityOther).hasFieldOrPropertyWithValue("supplierId", SUPPLIER_ID_OTHER);
        assertThat(entityOther).hasFieldOrPropertyWithValue("dateTime", DATE_TIME_OTHER);
        assertThat(entityOther).hasFieldOrPropertyWithValue("content", CONTENT_OTHER);
    }

    @Test
    void should_update_supplier_transaction_by_id() {
        var entity1 = entity();
        repository.save(entity1);

        var entity2 = entity();
        repository.save(entity2);

        var entity = repository.findById(entity2.getId()).get();
        entity.setId(entity2.getId());
        entity.setSupplierId(entity2.getSupplierId());
        entity.setDateTime(entity2.getDateTime());
        entity.setContent(entity2.getContent());
        repository.save(entity);

        var checkEntity = repository.findById(entity1.getId()).get();
        assertThat(checkEntity.getId()).isEqualTo(entity2.getId());
        assertThat(checkEntity.getSupplierId()).isEqualTo(entity2.getSupplierId());
        assertThat(checkEntity.getDateTime()).isEqualTo(entity2.getDateTime());
        assertThat(checkEntity.getContent()).isEqualTo(entity2.getContent());
    }

    SupplierTransaction entityOther() {
        var entity = new SupplierTransaction();
        entity.setId(ID_OTHER);
        entity.setSupplierId(SUPPLIER_ID_OTHER);
        entity.setDateTime(DATE_TIME_OTHER);
        entity.setContent(CONTENT_OTHER);
        return entity;
    }

    SupplierTransaction entity() {
        var entity = new SupplierTransaction();
        entity.setId(ID);
        entity.setSupplierId(SUPPLIER_ID);
        entity.setDateTime(DATE_TIME);
        entity.setContent(CONTENT);
        return entity;
    }


}
