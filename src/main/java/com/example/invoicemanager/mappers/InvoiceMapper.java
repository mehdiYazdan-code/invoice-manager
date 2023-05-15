package com.example.invoicemanager.mappers;

import com.example.invoicemanager.dto.InvoiceDto;
import com.example.invoicemanager.entities.Invoice;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(target = "status.id" , source="statusId")
    @Mapping(source = "contractId", target = "contract.id")
    Invoice toEntity(InvoiceDto invoiceDto);
    @Mapping(target = "statusId" , source="status.id")
    @Mapping(source = "contract.id", target = "contractId")
    @Mapping(source = "contract.customer.id", target = "customerID")
    InvoiceDto toDto(Invoice invoice);

    @Mapping(source = "contractId", target = "contract.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Invoice partialUpdate(InvoiceDto invoiceDto, @MappingTarget Invoice invoice);

    @AfterMapping
    default void linkItems(@MappingTarget Invoice invoice) {
        invoice.getItems().forEach(item -> item.setInvoice(invoice));
    }
}
