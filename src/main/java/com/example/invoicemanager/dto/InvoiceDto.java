package com.example.invoicemanager.dto;

import com.example.invoicemanager.enums.InvoiceStatus;
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
    private LocalDate issuedDate;
    private LocalDate dueDate;
    private InvoiceStatus status;
    private List<InvoiceItemDto> items;
}