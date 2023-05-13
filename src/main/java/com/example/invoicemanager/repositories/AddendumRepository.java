package com.example.invoicemanager.repositories;

import com.example.invoicemanager.entities.Addendum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddendumRepository extends JpaRepository<Addendum, Long> {
}