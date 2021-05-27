package com.rosouza.supplier.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rosouza.supplier.domain.SupplierTransaction;

@Repository
public interface SupplierTransactionRepository extends CrudRepository<SupplierTransaction, String>{

}
