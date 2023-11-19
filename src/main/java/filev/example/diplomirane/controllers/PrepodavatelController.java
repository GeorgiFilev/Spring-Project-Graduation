package filev.example.diplomirane.controllers;

import filev.example.diplomirane.entities.Dto.PrepodavatelDto;
import filev.example.diplomirane.entities.models.PrepodavatelEntity;
import filev.example.diplomirane.mappers.Mapper;
import filev.example.diplomirane.services.PrepodavatelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PrepodavatelController {
    private PrepodavatelService prepodavatelService;

    private Mapper<PrepodavatelEntity, PrepodavatelDto> prepodavatelMapper;

    public PrepodavatelController(PrepodavatelService prepodavatelService, Mapper<PrepodavatelEntity, PrepodavatelDto> prepodavatelMapper) {
        this.prepodavatelService = prepodavatelService;
        this.prepodavatelMapper = prepodavatelMapper;
    }


    @PostMapping(path = "/prepodavatels")
    public ResponseEntity<PrepodavatelDto> createPrepodavatel(@RequestBody PrepodavatelDto prepodavatel){
        PrepodavatelEntity prepodavatelEntity = prepodavatelMapper.mapFrom(prepodavatel);
        PrepodavatelEntity savedPrepodavatelEntity = prepodavatelService.save(prepodavatelEntity);

        return new ResponseEntity<>(prepodavatelMapper.mapTo(savedPrepodavatelEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/prepodavatels")
    public List<PrepodavatelDto> listPrepodavatels(){
        List<PrepodavatelEntity> prepodavatels = prepodavatelService.findAll();
        return prepodavatels.stream()
                .map(prepodavatelMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/prepodavatels/{id}")
    public ResponseEntity<PrepodavatelDto> getPrepodavatel(@PathVariable("id") Long id){
        Optional<PrepodavatelEntity> foundPrepodavatel = prepodavatelService.findOne(id);
        return foundPrepodavatel.map(prepodavatelEntity -> {
            PrepodavatelDto prepodavatelDto = prepodavatelMapper.mapTo(prepodavatelEntity);
            return new ResponseEntity(prepodavatelDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path="/prepodavatels/{id}")
    public ResponseEntity<PrepodavatelDto> fullUpdateAuthor(
            @PathVariable("id")Long id,
            @RequestBody PrepodavatelDto prepodavatelDto){
        if(!prepodavatelService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prepodavatelDto.setId(id);
        PrepodavatelEntity prepodavatelEntity = prepodavatelMapper.mapFrom(prepodavatelDto);
        PrepodavatelEntity savedPrepodavatelEntity = prepodavatelService.save(prepodavatelEntity);
        return new ResponseEntity<>(
                prepodavatelMapper.mapTo(savedPrepodavatelEntity),
                HttpStatus.OK
        );

    }

    @DeleteMapping(path = "/prepodavatel/{id}")
    public ResponseEntity deletePrepodavatel(@PathVariable("id")Long id ){
        prepodavatelService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
