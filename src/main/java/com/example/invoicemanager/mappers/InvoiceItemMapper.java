package com.example.invoicemanager.mappers;

import com.example.invoicemanager.dto.InvoiceItemDto;
import com.example.invoicemanager.entities.InvoiceItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InvoiceItemMapper {
    InvoiceItem invoiceItemDtoToInvoiceItem(InvoiceItemDto invoiceItemDto);

    InvoiceItemDto invoiceItemToInvoiceItemDto(InvoiceItem invoiceItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InvoiceItem updateInvoiceItemFromInvoiceItemDto(InvoiceItemDto invoiceItemDto, @MappingTarget InvoiceItem invoiceItem);
}
