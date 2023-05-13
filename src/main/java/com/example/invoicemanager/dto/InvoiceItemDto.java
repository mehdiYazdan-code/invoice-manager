package com.example.invoicemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto implements Serializable {
    private Long id;
    private Long unitPrice;
    private Long quantity;
}