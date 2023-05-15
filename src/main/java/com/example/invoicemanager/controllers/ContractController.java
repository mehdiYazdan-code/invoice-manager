package com.example.invoicemanager.controllers;

import com.example.invoicemanager.dtos.ContractDto;
import com.example.invoicemanager.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@Controller
@RequestMapping(path = "/api/contracts")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping(path = {"/",""})
    public ResponseEntity<List<ContractDto>> getAllContracts() {
        List<ContractDto> contractDtoList = contractService.getAllContracts();
        return ResponseEntity.ok(contractDtoList);
    }

    @PostMapping(path = {"/",""})
    public ResponseEntity<ContractDto> create(@RequestBody ContractDto contractDto) {
        ContractDto savedContractDto = contractService.createContract(contractDto);
        return new ResponseEntity<>(savedContractDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContractDto> findById(@PathVariable("id") Long id) {
        ContractDto contractDto = contractService.getContractById(id);
        if (contractDto != null) {
            return new ResponseEntity<>(contractDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ContractDto> update(@PathVariable("id") Long id, @RequestBody ContractDto contractDto) {
        ContractDto savedContractDto = contractService.updateContract(id, contractDto);
        if (savedContractDto != null) {
            return new ResponseEntity<>(savedContractDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
             contractService.deleteContract(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


