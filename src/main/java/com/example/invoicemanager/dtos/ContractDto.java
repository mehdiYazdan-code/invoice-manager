package com.example.invoicemanager.dtos;

import com.example.invoicemanager.enums.BarrelType;
import com.example.invoicemanager.enums.ContractType;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractDto {

    private Long id;

    private String contractNumber;

    private String contractDescription;

    private Long customerId;

    private Long unitPrice;

    private Long quantity;

    private ContractType type;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long advancePayment;

    private Long performanceBond;

    private Long totalAmount;

    private Long totalBarrels;

    private BarrelType barrelType;

}

