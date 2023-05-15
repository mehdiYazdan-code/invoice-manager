package com.example.invoicemanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto implements Serializable {
    private Long id;
    private String contractNumber;
    private String contractDescription;
    private Long customerId;
    private Long unitPrice;
    private Long quantity;
    private Integer barrelTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long advancePayment;
    private Long performanceBond;

    private Long insuranceDeposit;

    private Long totalAmount;
    private Long totalBarrels;
}