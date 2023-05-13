package com.example.invoicemanager.mappers;

import com.example.invoicemanager.dto.AddendumDto;
import com.example.invoicemanager.entities.Addendum;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AddendumMapper {
    @Mapping(source = "contractId", target = "contract.id")
    Addendum addendumDtoToAddendum(AddendumDto addendumDto);

    @Mapping(source = "contract.id", target = "contractId")
    AddendumDto addendumToAddendumDto(Addendum addendum);

    @Mapping(source = "contractId", target = "contract.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Addendum updateAddendumFromAddendumDto(AddendumDto addendumDto, @MappingTarget Addendum addendum);
}
