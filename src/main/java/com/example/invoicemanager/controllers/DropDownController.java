package com.example.invoicemanager.controllers;

import com.example.invoicemanager.dto.CustomerDropDownDto;
import com.example.invoicemanager.mappers.CustomerDropDownMapper;
import com.example.invoicemanager.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/drop-down")
public class DropDownController {

    private final CustomerRepository customerRepository;
    private final CustomerDropDownMapper customerDropDownMapper;

    @Autowired
    public DropDownController(CustomerRepository customerRepository, CustomerDropDownMapper customerDropDownMapper) {
        this.customerRepository = customerRepository;
        this.customerDropDownMapper = customerDropDownMapper;
    }
    @GetMapping(path = "/customers")
    public ResponseEntity<List<CustomerDropDownDto>> getCustomerDropDown(){
        List<CustomerDropDownDto> dropDownDtoList = customerRepository.findAll().stream().map(customerDropDownMapper::toDto).toList();
        return ResponseEntity.ok(dropDownDtoList);
    }
}
