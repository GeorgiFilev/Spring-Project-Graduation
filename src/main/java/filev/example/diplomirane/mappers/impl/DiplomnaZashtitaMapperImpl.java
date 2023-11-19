package filev.example.diplomirane.mappers.impl;

import filev.example.diplomirane.entities.Dto.DiplomnaZashtitaDto;
import filev.example.diplomirane.entities.models.DiplomnaZashtitaEntity;
import filev.example.diplomirane.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DiplomnaZashtitaMapperImpl implements Mapper<DiplomnaZashtitaEntity, DiplomnaZashtitaDto> {
    private ModelMapper modelMapper;

    public DiplomnaZashtitaMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DiplomnaZashtitaDto mapTo(DiplomnaZashtitaEntity diplomnaZashtitaEntity) {
        return modelMapper.map(diplomnaZashtitaEntity, DiplomnaZashtitaDto.class);
    }

    @Override
    public DiplomnaZashtitaEntity mapFrom(DiplomnaZashtitaDto diplomnaZashtitaDto) {
        return modelMapper.map(diplomnaZashtitaDto, DiplomnaZashtitaEntity.class);
    }
}
