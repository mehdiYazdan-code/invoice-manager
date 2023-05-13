package com.example.invoicemanager.services;

import com.example.invoicemanager.dtos.ContractDto;
import com.example.invoicemanager.entities.Contract;
import com.example.invoicemanager.entities.Customer;
import com.example.invoicemanager.repositories.ContractRepository;
import com.example.invoicemanager.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository, CustomerRepository customerRepository) {
        this.contractRepository = contractRepository;
        this.customerRepository = customerRepository;
    }

    public List<ContractDto> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        List<ContractDto> contractDtoList = new ArrayList<>();
        for (Contract contract : contracts) {
            ContractDto contractDto = convertToDto(contract);
            contractDtoList.add(contractDto);
        }
        return contractDtoList;
    }

    public ContractDto createContract(ContractDto contractDto) {
        Contract contract = convertToEntity(contractDto);
        contract.setCustomer(getCustomerById(contractDto.getCustomerId()));
        Contract savedContract = contractRepository.save(contract);
        return convertToDto(savedContract);
    }

    public ContractDto getContractById(Long id) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            return convertToDto(contract);
        } else {
            return null;
        }
    }

    public ContractDto updateContract(Long id, ContractDto contractDto) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            contract.setContractNumber(contractDto.getContractNumber());
            contract.setContractDescription(contractDto.getContractDescription());
            contract.setUnitPrice(contractDto.getUnitPrice());
            contract.setQuantity(contractDto.getQuantity());
            contract.setBarrelType(contractDto.getBarrelType());
            contract.setStartDate(contractDto.getStartDate());
            contract.setEndDate(contractDto.getEndDate());
            contract.setAdvancePayment(contractDto.getAdvancePayment());
            contract.setPerformanceBond(contractDto.getPerformanceBond());
            contract.setCustomer(customerRepository.getById(contractDto.getCustomerId()));
            Contract savedContract = contractRepository.save(contract);
            return convertToDto(savedContract);
        } else {
            return null;
        }
    }

    public void deleteContract(Long id) throws InterruptedIOException {
        try {
             contractRepository.deleteById(id);

        } catch (Exception e) {
            throw new InterruptedIOException("internal server error");
        }
    }

    private ContractDto convertToDto(Contract contract) {
        ContractDto contractDto = new ContractDto();
        contractDto.setId(contract.getId());
        contractDto.setContractNumber(contract.getContractNumber());
        contractDto.setContractDescription(contract.getContractDescription());
        contractDto.setUnitPrice(contract.getUnitPrice());
        contractDto.setQuantity(contract.getQuantity());
        contractDto.setBarrelType(contract.getBarrelType());
        contractDto.setStartDate(contract.getStartDate());
        contractDto.setEndDate(contract.getEndDate());
        contractDto.setAdvancePayment(contract.getAdvancePayment());
        contractDto.setPerformanceBond(contract.getPerformanceBond());
        contractDto.setCustomerId(contract.getCustomer().getId());
        contractDto.setTotalBarrels(contract.getTotalCommittedBarrels());
        contractDto.setTotalAmount(contract.getTotalAmount());
        return contractDto;
    }

    private Contract convertToEntity(ContractDto contractDto) {
        Contract contract = new Contract();
        contract.setContractNumber(contractDto.getContractNumber());
        contract.setContractDescription(contractDto.getContractDescription());
        contract.setUnitPrice(contractDto.getUnitPrice());
        contract.setQuantity(contractDto.getQuantity());
        contract.setBarrelType(contractDto.getBarrelType());
        contract.setStartDate(contractDto.getStartDate());
        contract.setEndDate(contractDto.getEndDate());
        contract.setAdvancePayment(contractDto.getAdvancePayment());
        contract.setPerformanceBond(contractDto.getPerformanceBond());
        return contract;
    }
    private Customer getCustomerById(Long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElse(null);
    }
}
