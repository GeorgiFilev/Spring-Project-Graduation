package filev.example.diplomirane.controllers;

import filev.example.diplomirane.entities.Dto.ZadanieDto;
import filev.example.diplomirane.entities.models.ZadanieEntity;
import filev.example.diplomirane.mappers.Mapper;
import filev.example.diplomirane.services.ZadanieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ZadanieController {

    private ZadanieService zadanieService;

    private Mapper<ZadanieEntity, ZadanieDto> zadanieMapper;

    public ZadanieController(ZadanieService zadanieService, Mapper<ZadanieEntity, ZadanieDto> zadanieMapper) {
        this.zadanieService = zadanieService;
        this.zadanieMapper = zadanieMapper;
    }

    @PostMapping(path = "/zadanies")
    public ResponseEntity<ZadanieDto> createZadanie(@RequestBody ZadanieDto zadanie){
        ZadanieEntity zadanieEntity = zadanieMapper.mapFrom(zadanie);
        ZadanieEntity savedZadanieEntity = zadanieService.save(zadanieEntity);
        return new ResponseEntity<>(zadanieMapper.mapTo(savedZadanieEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/zadanies")
    public List<ZadanieDto> ListZadanies(){
        List<ZadanieEntity> zadanies = zadanieService.findAll();
        return  zadanies.stream()
                .map(zadanie -> {
                    return zadanieMapper.mapTo(zadanie);
                })
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/zadanies/odobreno/{odobreno}")
    public List<ZadanieDto> ListZadanies(@PathVariable("odobreno") String odobreno){
        List<ZadanieEntity> zadanies = zadanieService.findByOdobreno(odobreno);
        return  zadanies.stream()
                .map(zadanie -> {
                    return zadanieMapper.mapTo(zadanie);
                })
                .collect(Collectors.toList());
    }


    @GetMapping(path = "/zadanies/{id}")
    public ResponseEntity<ZadanieDto> getAuthor(@PathVariable("id") Long id){
        Optional<ZadanieEntity> foundZadanie = zadanieService.findOne(id);
        return foundZadanie.map(zadanieEntity -> {
            ZadanieDto zadanieDto = zadanieMapper.mapTo(zadanieEntity);
            return new ResponseEntity(zadanieDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/zadanies/{id}")
    public ResponseEntity deleteZadanie(@PathVariable("id")Long id){
        zadanieService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
