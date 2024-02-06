package com.eazybytes.accounts2.repository;

import com.eazybytes.accounts2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long > {

    Optional<Customer> findByMobileNumber(String mobileNumber);
}
