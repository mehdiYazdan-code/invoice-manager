package com.example.invoicemanager.services;

import com.example.invoicemanager.dtos.CustomerDto;
import com.example.invoicemanager.entities.Customer;
import com.example.invoicemanager.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = convertToDto(customer);
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }

    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return convertToDto(customer);
        } else {
            return null;
        }
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setName(customerDto.getName());
            customer.setNationalId(customerDto.getNationalId());
            customer.setEconomicCode(customerDto.getEconomicCode());
            customer.setRegisterNumber(customerDto.getRegisterNumber());
            customer.setPhone(customerDto.getPhone());
            customer.setAddress(customerDto.getAddress());
            Customer savedCustomer = customerRepository.save(customer);
            return convertToDto(savedCustomer);
        } else {
            return null;
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setNationalId(customer.getNationalId());
        customerDto.setEconomicCode(customer.getEconomicCode());
        customerDto.setRegisterNumber(customer.getRegisterNumber());
        customerDto.setPhone(customer.getPhone());
        customerDto.setAddress(customer.getAddress());
        return customerDto;
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setNationalId(customerDto.getNationalId());
        customer.setEconomicCode(customerDto.getEconomicCode());
        customer.setRegisterNumber(customerDto.getRegisterNumber());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        return customer;
    }

}
