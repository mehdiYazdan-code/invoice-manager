package com.example.invoicemanager.controllers;

import com.example.invoicemanager.dtos.BarrelTypeDto;
import com.example.invoicemanager.entities.BarrelType;
import com.example.invoicemanager.entities.InvoiceStatus;
import com.example.invoicemanager.repositories.InvoiceStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/invoice-statuses")
public class InvoiceStatusController {
    private final InvoiceStatusRepository invoiceStatusRepository;
    @Autowired
    public InvoiceStatusController(InvoiceStatusRepository invoiceStatusRepository) {
        this.invoiceStatusRepository = invoiceStatusRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceStatus> getInvoiceStatus(@PathVariable Integer id) {
        InvoiceStatus invoiceStatus = invoiceStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("status type not found"));
        return ResponseEntity.ok(invoiceStatus);
    }

    @GetMapping(path = {"/",""})
    public ResponseEntity<List<InvoiceStatus>> getAllBarrelTypes() {
        List<InvoiceStatus> statusList = invoiceStatusRepository.findAll();
        return ResponseEntity.ok(statusList);
    }

    @PostMapping(path = {"/",""})
    public ResponseEntity<InvoiceStatus> createBarrelType(@RequestBody InvoiceStatus invoiceStatus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceStatusRepository.save(invoiceStatus));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InvoiceStatus> updateBarrelType(@PathVariable Integer id, @RequestBody InvoiceStatus invoiceStatus) {
        InvoiceStatus persist = invoiceStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("barrel type with id: " + id + " not found"));
        persist.setName(invoiceStatus.getName());
        InvoiceStatus save = invoiceStatusRepository.save(persist);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteBarrelType(@PathVariable Integer id) {
        invoiceStatusRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
