package com.Banking.Retail_Banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.Retail_Banking.models.Transaction;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    
    List<Transaction> findBySourceAccountIdOrderByInitiationDate(long id);
}
