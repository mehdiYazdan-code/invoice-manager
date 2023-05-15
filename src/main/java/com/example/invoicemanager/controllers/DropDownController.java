package com.example.invoicemanager.controllers;

import com.example.invoicemanager.dto.ContractDropDownDto;
import com.example.invoicemanager.dto.CustomerDropDownDto;
import com.example.invoicemanager.mappers.ContractDropDownMapper;
import com.example.invoicemanager.mappers.CustomerDropDownMapper;
import com.example.invoicemanager.repositories.ContractRepository;
import com.example.invoicemanager.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/drop-down")
public class DropDownController {

    private final CustomerRepository customerRepository;
    private final CustomerDropDownMapper customerDropDownMapper;
    private final ContractRepository contractRepository;
    private final ContractDropDownMapper contractDropDownMapper;

    @Autowired
    public DropDownController(CustomerRepository customerRepository,
                              CustomerDropDownMapper customerDropDownMapper,
                              ContractRepository contractRepository,
                              ContractDropDownMapper contractDropDownMapper) {
        this.customerRepository = customerRepository;
        this.customerDropDownMapper = customerDropDownMapper;
        this.contractRepository = contractRepository;
        this.contractDropDownMapper = contractDropDownMapper;
    }
    @GetMapping(path = "/customers")
    public ResponseEntity<List<CustomerDropDownDto>> getCustomerDropDown(){
        List<CustomerDropDownDto> dropDownDtoList = customerRepository.findAll().stream().map(customerDropDownMapper::toDto).toList();
        return ResponseEntity.ok(dropDownDtoList);
    }
    @GetMapping(path = "/contracts/{customerId}")
    public ResponseEntity<List<ContractDropDownDto>> getContractDropDown(@PathVariable("customerId") Long customerId){
        List<ContractDropDownDto> dropDownDtoList = contractRepository.findAllByCustomerId(customerId).stream().map(contractDropDownMapper::toDto).toList();
        return ResponseEntity.ok(dropDownDtoList);
    }
}
