package com.example.invoicemanager.services;

import com.example.invoicemanager.dtos.AddendumDto;
import com.example.invoicemanager.entities.Addendum;
import com.example.invoicemanager.repositories.AddendumRepository;
import com.example.invoicemanager.repositories.ContractRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddendumService {
    private final AddendumRepository addendumRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public AddendumService(AddendumRepository addendumRepository, ContractRepository contractRepository) {
        this.addendumRepository = addendumRepository;
        this.contractRepository = contractRepository;
    }

    public List<AddendumDto> getAllAddendumList() {
        List<Addendum> addendumList = addendumRepository.findAll();
        return addendumList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public AddendumDto getAddendumById(Long id) throws EntityNotFoundException {
        Addendum addendum = addendumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Addendum not found with id: " + id));
        return convertToDto(addendum);
    }


    public AddendumDto createAddendum(AddendumDto addendumDto) {
        Addendum addendum = new Addendum();
        addendum.setId(addendumDto.getId());
        addendum.setBarrelType(addendumDto.getBarrelType());
        addendum.setContract(contractRepository.findById(addendumDto.getContractId()).orElse(null));
        addendum.setUnitPrice(addendumDto.getUnitPrice());
        addendum.setQuantity(addendumDto.getQuantity());
        addendum.setStartDate(addendumDto.getStartDate());
        addendum.setEndDate(addendumDto.getEndDate());
        addendum = addendumRepository.save(addendum);
        return convertToDto(addendum);
    }



    public AddendumDto updateAddendum(Long id, AddendumDto addendumDto) throws EntityNotFoundException {
        Addendum addendum = addendumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Addendum not found with id: " + id));
        addendum.setContract(contractRepository.findById(addendumDto.getContractId()).orElse(null));
        addendum.setUnitPrice(addendumDto.getUnitPrice());
        addendum.setQuantity(addendumDto.getQuantity());
        addendum.setStartDate(addendumDto.getStartDate());
        addendum.setEndDate(addendumDto.getEndDate());
        addendum = addendumRepository.save(addendum);
        return convertToDto(addendum);
    }


    public void deleteAddendum(Long id) throws EntityNotFoundException {
        Addendum addendum = addendumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Addendum not found with id: " + id));

        addendumRepository.delete(addendum);
    }

    private AddendumDto convertToDto(Addendum addendum) {
        AddendumDto addendumDto = new AddendumDto();
        addendumDto.setId(addendum.getId());
        addendumDto.setContractId(addendum.getContract().getId());
        addendumDto.setUnitPrice(addendum.getUnitPrice());
        addendumDto.setQuantity(addendum.getQuantity());
        addendumDto.setStartDate(addendum.getStartDate());
        addendumDto.setEndDate(addendum.getEndDate());
        return addendumDto;
    }

}





