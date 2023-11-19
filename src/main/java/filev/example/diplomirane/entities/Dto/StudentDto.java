package filev.example.diplomirane.entities.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import filev.example.diplomirane.entities.models.DiplomnaZashtitaEntity;
import filev.example.diplomirane.entities.models.ZadanieEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StudentDto {

    private Long id;

    private String name;

    private String fNumber;

    private List<ZadanieEntity> zadaniq;

    private DiplomnaZashtitaEntity diplomnaZashtita;
}
