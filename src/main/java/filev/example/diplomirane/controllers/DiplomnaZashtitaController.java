package filev.example.diplomirane.controllers;


import filev.example.diplomirane.entities.Dto.DiplomnaZashtitaDto;
import filev.example.diplomirane.entities.models.DiplomnaZashtitaEntity;
import filev.example.diplomirane.mappers.Mapper;
import filev.example.diplomirane.services.DiplomnaZashtitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DiplomnaZashtitaController {

    private DiplomnaZashtitaService diplomnaZashtitaService;
    private Mapper<DiplomnaZashtitaEntity, DiplomnaZashtitaDto> diplomnaZashtitaMapper;

    @Autowired
    public DiplomnaZashtitaController(DiplomnaZashtitaService diplomnaZashtitaService, Mapper<DiplomnaZashtitaEntity, DiplomnaZashtitaDto> diplomnaZashtitaMapper) {
        this.diplomnaZashtitaService = diplomnaZashtitaService;
        this.diplomnaZashtitaMapper = diplomnaZashtitaMapper;
    }

    @PostMapping(path = "/diplomnaZashtitas")
    public ResponseEntity<DiplomnaZashtitaDto> createDiplomnaZashtita(@RequestBody DiplomnaZashtitaDto diplomnaZashtita){
        DiplomnaZashtitaEntity diplomnaZashtitaEntity = diplomnaZashtitaMapper.mapFrom(diplomnaZashtita);
        DiplomnaZashtitaEntity savedDiplomnaZashtita = diplomnaZashtitaService.save(diplomnaZashtitaEntity);
        return new ResponseEntity<>(diplomnaZashtitaMapper.mapTo(savedDiplomnaZashtita), HttpStatus.CREATED);
    }

    @GetMapping
    public List<DiplomnaZashtitaDto> listDiplomnaZashtitas(){
        List<DiplomnaZashtitaEntity> diplomnaZashtitas = diplomnaZashtitaService.findAll();
        return diplomnaZashtitas.stream()
                .map(diplomnaZashtitaMapper::mapTo)
                .collect(Collectors.toList());
    }


    @DeleteMapping(path = "/diplomnaZashtitas/{id}")
    public ResponseEntity deleteDiplomnaZashtita(@PathVariable("id")Long id){
        diplomnaZashtitaService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
