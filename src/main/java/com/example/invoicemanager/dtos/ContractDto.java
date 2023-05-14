package com.example.invoicemanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.example.invoicemanager.entities.Contract} entity
 */
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
    private Long totalAmount;
    private Long totalBarrels;
}