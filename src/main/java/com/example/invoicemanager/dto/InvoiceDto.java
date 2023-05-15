package com.example.invoicemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto implements Serializable {
    private Long id;
    private Long contractId;
    private Long customerID;
    private LocalDate issuedDate;
    private LocalDate dueDate;
    private Integer statusId;
    private List<InvoiceItemDto> items;
}