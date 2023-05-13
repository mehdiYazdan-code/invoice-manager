package com.example.invoicemanager.repositories;

import com.example.invoicemanager.entities.BarrelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarrelTypeRepository extends JpaRepository<BarrelType, Integer> {
}