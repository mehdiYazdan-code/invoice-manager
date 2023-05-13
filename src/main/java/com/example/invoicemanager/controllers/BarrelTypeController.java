package com.example.invoicemanager.controllers;

import com.example.invoicemanager.dtos.BarrelTypeDto;
import com.example.invoicemanager.entities.BarrelType;
import com.example.invoicemanager.repositories.BarrelTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/api/barrel-types")
public class BarrelTypeController {
    private final BarrelTypeRepository barrelTypeRepository;

    public BarrelTypeController(BarrelTypeRepository barrelTypeRepository) {
        this.barrelTypeRepository = barrelTypeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarrelTypeDto> getBarrelType(@PathVariable Integer id) {
        BarrelType barrelType = barrelTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Barrel type not found"));

        BarrelTypeDto barrelTypeDto = mapBarrelTypeToDto(barrelType);

        return ResponseEntity.ok(barrelTypeDto);
    }

    @GetMapping(path = {"/",""})
    public ResponseEntity<List<BarrelTypeDto>> getAllBarrelTypes() {
        List<BarrelType> barrelTypes = barrelTypeRepository.findAll();

        List<BarrelTypeDto> barrelTypeDtos = barrelTypes.stream()
                .map(this::mapBarrelTypeToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(barrelTypeDtos);
    }

    @PostMapping(path = {"/",""})
    public ResponseEntity<BarrelTypeDto> createBarrelType(@RequestBody BarrelTypeDto barrelTypeDto) {
        BarrelType barrelType = mapDtoToBarrelType(barrelTypeDto);

        BarrelType savedBarrelType = barrelTypeRepository.save(barrelType);

        BarrelTypeDto savedBarrelTypeDto = mapBarrelTypeToDto(savedBarrelType);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBarrelTypeDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BarrelTypeDto> updateBarrelType(@PathVariable Integer id, @RequestBody BarrelTypeDto barrelTypeDto) {
        BarrelType barrelType = barrelTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("barrel type with id: " + id + " not found"));

        barrelType.setName(barrelTypeDto.getName());

        BarrelType updatedBarrelType = barrelTypeRepository.save(barrelType);

        BarrelTypeDto updatedBarrelTypeDto = mapBarrelTypeToDto(updatedBarrelType);

        return ResponseEntity.ok(updatedBarrelTypeDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteBarrelType(@PathVariable Integer id) {
        barrelTypeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private BarrelTypeDto mapBarrelTypeToDto(BarrelType barrelType) {
        return new BarrelTypeDto(barrelType.getId(), barrelType.getName());
    }

    private BarrelType mapDtoToBarrelType(BarrelTypeDto barrelTypeDto) {
        BarrelType barrelType = new BarrelType();
        barrelType.setName(barrelTypeDto.getName());

        return barrelType;
    }
}

