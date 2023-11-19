package filev.example.diplomirane.mappers.impl;

import filev.example.diplomirane.entities.Dto.StudentDto;
import filev.example.diplomirane.entities.models.StudentEntity;
import filev.example.diplomirane.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperImpl implements Mapper<StudentEntity, StudentDto> {
    private ModelMapper modelMapper;

    public StudentMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }


    @Override
    public StudentDto mapTo(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity,StudentDto.class);
    }


    @Override
    public StudentEntity mapFrom(StudentDto studentDto) {
        return modelMapper.map(studentDto,StudentEntity.class);
    }
}
