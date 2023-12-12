package filev.example.diplomirane.services.impl;

import filev.example.diplomirane.entities.models.ZadanieEntity;
import filev.example.diplomirane.repositories.ZadanieRepository;
import filev.example.diplomirane.services.ZadanieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ZadanieServiceImpl  implements ZadanieService {

    private ZadanieRepository zadanieRepository;

    public ZadanieServiceImpl(ZadanieRepository zadanieRepository){
        this.zadanieRepository = zadanieRepository;
    }


    @Override
    public ZadanieEntity save(ZadanieEntity zadanieEntity) {
        // задаваме дефолтно не одобрено задание
        zadanieEntity.setOdobreno("no");
        return this.zadanieRepository.save(zadanieEntity);
    }

    @Override
    public List<ZadanieEntity> findAll() {
        return StreamSupport.stream(
                this.zadanieRepository.findAll()
                .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ZadanieEntity> findOne(Long id) {
        return zadanieRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        zadanieRepository.deleteById(id);
    }

    @Override
    public List<ZadanieEntity> findByOdobreno(String odobreno) {
        return StreamSupport.stream(zadanieRepository
                        .findByOdobreno(odobreno)
                        .spliterator(),false)
                .collect(Collectors.toList());
    }

}
