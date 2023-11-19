package filev.example.diplomirane.mappers.impl;

import filev.example.diplomirane.entities.Dto.ZadanieDto;
import filev.example.diplomirane.entities.models.ZadanieEntity;
import filev.example.diplomirane.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ZadanieMapperImpl implements Mapper<ZadanieEntity, ZadanieDto> {
    private ModelMapper modelMapper;

    public ZadanieMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ZadanieDto mapTo(ZadanieEntity zadanieEntity) {
        return modelMapper.map(zadanieEntity, ZadanieDto.class);
    }

    @Override
    public ZadanieEntity mapFrom(ZadanieDto zadanieDto) {
        return modelMapper.map(zadanieDto, ZadanieEntity.class);
    }
}
