package com.Banking.Retail_Banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.Retail_Banking.models.Account;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findBySortCodeAndAccountNumber(String sortCode, String accountNumber);
    Optional<Account> findByAccountNumber(String accountNumber);
}
