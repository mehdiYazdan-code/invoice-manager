package com.example.invoicemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDropDownDto implements Serializable {
    private Long id;
    private String contractNumber;
    private String contractDescription;
    private Long totalAmount;
    private Long totalBarrels;
}