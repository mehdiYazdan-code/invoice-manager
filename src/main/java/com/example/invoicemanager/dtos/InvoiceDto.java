package com.example.invoicemanager.dtos;

import com.example.invoicemanager.dto.InvoiceItemDto;
import com.example.invoicemanager.enums.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDto {
    private Long id;
    private Long contractId;
    private LocalDate issuedDate;
    private LocalDate dueDate;
    private InvoiceStatus status;
    private Long amount;
    private List<InvoiceItemDto> items;
}
