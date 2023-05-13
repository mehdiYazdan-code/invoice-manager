package com.example.invoicemanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String nationalId;
    private String economicCode;
    private String registerNumber;
    private String phone;
    private String address;
}

