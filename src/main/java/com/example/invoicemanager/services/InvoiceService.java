package com.example.invoicemanager.services;


import com.example.invoicemanager.dto.InvoiceDto;
import com.example.invoicemanager.entities.Invoice;
import com.example.invoicemanager.mappers.InvoiceMapper;
import com.example.invoicemanager.repositories.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    public List<InvoiceDto> getAllInvoices() {
       return invoiceRepository.findAll().stream().map(invoiceMapper::toDto).collect(Collectors.toList());
    }

    public InvoiceDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice with id: " + id));
        return invoiceMapper.toDto(invoice);
    }

    public InvoiceDto createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDto);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(savedInvoice);
    }
    public InvoiceDto updateInvoice(Long id,InvoiceDto invoiceDto){
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice with id: " + id));
        Invoice updateInvoiceFromInvoiceDto = invoiceMapper.partialUpdate(invoiceDto, invoice);
        Invoice updated = invoiceRepository.save(updateInvoiceFromInvoiceDto);
        return invoiceMapper.toDto(updated);
    }

    public void deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice with id: " + id));
        invoiceRepository.delete(invoice);
    }

}
