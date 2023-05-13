package com.example.invoicemanager.controllers;

import com.example.invoicemanager.dto.InvoiceDto;
import com.example.invoicemanager.services.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = {"/",""})
    public ResponseEntity<List<InvoiceDto>> getAll() {
        List<InvoiceDto> allInvoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(allInvoices);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        InvoiceDto invoiceDto = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoiceDto);
    }

    @PostMapping(path = {"/",""})
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody @Valid InvoiceDto invoiceDto1) {
        InvoiceDto invoiceDto = invoiceService.createInvoice(invoiceDto1);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(invoiceDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(invoiceDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InvoiceDto> update(@PathVariable("id") Long id, @RequestBody InvoiceDto invoiceDto) {
        InvoiceDto updatedInvoiceDto = invoiceService.updateInvoice(id, invoiceDto);
        return ResponseEntity.ok()
                .location(ServletUriComponentsBuilder.fromCurrentRequest()
                        .buildAndExpand(updatedInvoiceDto.getId()).toUri())
                .body(updatedInvoiceDto);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}

