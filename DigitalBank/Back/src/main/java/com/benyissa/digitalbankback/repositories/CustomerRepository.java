package com.benyissa.digitalbankback.repositories;

import com.benyissa.digitalbankback.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomerByFirstNameContainsOrLastNameContains(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
