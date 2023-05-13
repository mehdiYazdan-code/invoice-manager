package com.example.invoicemanager.mappers;

import com.example.invoicemanager.dto.InvoiceDto;
import com.example.invoicemanager.entities.Invoice;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(source = "contractId", target = "contract.id")
    Invoice invoiceDtoToInvoice(InvoiceDto invoiceDto);

    @Mapping(source = "contract.id", target = "contractId")
    InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    @Mapping(source = "contractId", target = "contract.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Invoice updateInvoiceFromInvoiceDto(InvoiceDto invoiceDto, @MappingTarget Invoice invoice);

    @AfterMapping
    default void linkItems(@MappingTarget Invoice invoice) {
        invoice.getItems().forEach(item -> item.setInvoice(invoice));
    }
}
