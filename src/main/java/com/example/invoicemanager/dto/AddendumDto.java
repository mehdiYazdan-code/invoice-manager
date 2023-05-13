package com.example.invoicemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.example.invoicemanager.entities.Addendum} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddendumDto implements Serializable {
    private Long id;
    private Long contractId;
    private Double unitPrice;
    private Long quantity;
    private LocalDate startDate;
    private LocalDate endDate;
}