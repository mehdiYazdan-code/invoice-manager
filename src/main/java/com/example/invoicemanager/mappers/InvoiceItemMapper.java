package com.example.invoicemanager.mappers;

import com.example.invoicemanager.dto.InvoiceItemDto;
import com.example.invoicemanager.entities.InvoiceItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InvoiceItemMapper {
    @Mapping(target = "barrelType.id" , source="barrelTypeId")
    InvoiceItem toEntity(InvoiceItemDto invoiceItemDto);
    @Mapping(target = "barrelTypeId" , source="barrelType.id")
    InvoiceItemDto toDto(InvoiceItem invoiceItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InvoiceItem partialUpdate(InvoiceItemDto invoiceItemDto, @MappingTarget InvoiceItem invoiceItem);
}
