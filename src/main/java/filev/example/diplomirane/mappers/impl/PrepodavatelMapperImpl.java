package filev.example.diplomirane.mappers.impl;

import filev.example.diplomirane.entities.Dto.PrepodavatelDto;
import filev.example.diplomirane.entities.models.PrepodavatelEntity;
import filev.example.diplomirane.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PrepodavatelMapperImpl implements Mapper<PrepodavatelEntity, PrepodavatelDto> {
    private ModelMapper modelMapper;

    public PrepodavatelMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PrepodavatelDto mapTo(PrepodavatelEntity prepodavatelEntity) {
        return modelMapper.map(prepodavatelEntity, PrepodavatelDto.class);
    }

    @Override
    public PrepodavatelEntity mapFrom(PrepodavatelDto prepodavatelDto) {
        return modelMapper.map(prepodavatelDto, PrepodavatelEntity.class);
    }
}
