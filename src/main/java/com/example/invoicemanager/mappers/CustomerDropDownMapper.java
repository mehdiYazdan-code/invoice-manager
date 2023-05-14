package com.example.invoicemanager.mappers;

import com.example.invoicemanager.dto.CustomerDropDownDto;
import com.example.invoicemanager.entities.Customer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerDropDownMapper {
    Customer toEntity(CustomerDropDownDto customerDropDownDto);

    CustomerDropDownDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerDropDownDto customerDropDownDto, @MappingTarget Customer customer);
}
