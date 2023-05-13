package com.example.invoicemanager.controllers;

import com.example.invoicemanager.dtos.CustomerDto;
import com.example.invoicemanager.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = {"/",""})
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        }
    }

    @PostMapping(path = {"/",""})
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomerDto = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = customerService.updateCustomer(id, customerDto);
        if (updatedCustomerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedCustomerDto, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

