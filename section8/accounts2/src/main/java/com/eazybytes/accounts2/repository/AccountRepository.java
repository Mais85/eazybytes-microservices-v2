package com.eazybytes.accounts2.repository;

import com.eazybytes.accounts2.entity.Accounts;
import com.eazybytes.accounts2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long > {
    Optional<Accounts> findByCustomerId(Long id);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
