package com.example.invoicemanager.mappers;

import com.example.invoicemanager.dtos.ContractDto;
import com.example.invoicemanager.entities.Contract;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContractMapper {
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "barrelTypeId", target = "barrelType.id")
    Contract toEntity(ContractDto contractDto);

    @Mapping(target = "totalAmount", expression = "java(contract.getTotalAmount())")
    @Mapping(target = "totalBarrels", expression = "java(contract.getTotalBarrels())")
    @Mapping(source = "barrelType.id", target = "barrelTypeId")
    @Mapping(source = "customer.id", target = "customerId")
    ContractDto toDto(Contract contract);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "barrelTypeId", target = "barrelType.id")
    Contract partialUpdate(ContractDto contractDto, @MappingTarget Contract contract);
}