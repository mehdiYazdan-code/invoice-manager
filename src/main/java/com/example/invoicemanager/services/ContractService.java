package com.example.invoicemanager.services;

import com.example.invoicemanager.dtos.ContractDto;
import com.example.invoicemanager.entities.Contract;
import com.example.invoicemanager.entities.Customer;
import com.example.invoicemanager.mappers.ContractMapper;
import com.example.invoicemanager.repositories.ContractRepository;
import com.example.invoicemanager.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository, ContractMapper contractMapper, CustomerRepository customerRepository) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
        this.customerRepository = customerRepository;
    }

    public List<ContractDto> getAllContracts() {
        return  contractRepository.findAll().stream().map(contractMapper::toDto).collect(Collectors.toList());

    }

    public ContractDto createContract(ContractDto contractDto) {
        Contract contract = contractMapper.toEntity(contractDto);
        Contract savedContract = contractRepository.save(contract);
        return contractMapper.toDto(savedContract);
    }

    public ContractDto getContractById(Long id) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            return contractMapper.toDto(contract);
        } else {
            return null;
        }
    }

    public ContractDto updateContract(Long id, ContractDto contractDto) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            Contract partialUpdate = contractMapper.partialUpdate(contractDto, contract);
            Contract saved = contractRepository.save(partialUpdate);
            return contractMapper.toDto(saved);
        }
        return null;
    }

    public void deleteContract(Long id) throws InterruptedIOException {
        try {
             contractRepository.deleteById(id);

        } catch (Exception e) {
            throw new InterruptedIOException("internal server error");
        }
    }


    private Customer getCustomerById(Long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElse(null);
    }
}
