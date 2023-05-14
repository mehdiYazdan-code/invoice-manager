package com.example.invoicemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.invoicemanager.entities.Customer} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private Long id;
    private String name;
    private String nationalId;
    private String economicCode;
    private String registerNumber;
    private String phone;
    private String address;
}