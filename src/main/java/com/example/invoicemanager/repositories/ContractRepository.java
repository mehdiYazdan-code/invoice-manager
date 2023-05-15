package com.example.invoicemanager.repositories;

import com.example.invoicemanager.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("select c from Contract c where c.customer.id = :id")
    List<Contract> findAllByCustomerId(@Param("id") Long id);
}