package com.example.invoicemanager.dtos;

import com.example.invoicemanager.entities.BarrelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddendumDto {
    private Long id;
    private BarrelType barrelType;
    private Long contractId;
    private Long unitPrice;
    private Long quantity;
    private LocalDate startDate;
    private LocalDate endDate;
}

