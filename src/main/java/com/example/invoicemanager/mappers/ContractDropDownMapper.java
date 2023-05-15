package com.example.invoicemanager.mappers;

import com.example.invoicemanager.dto.ContractDropDownDto;
import com.example.invoicemanager.entities.Contract;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ContractDropDownMapper {
    @Mapping(target = "totalAmount", expression = "java(contract.getTotalAmount())")
    @Mapping(target = "totalBarrels", expression = "java(contract.getTotalBarrels())")
    ContractDropDownDto toDto(Contract contract);
}
