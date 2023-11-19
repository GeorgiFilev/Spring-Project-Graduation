package filev.example.diplomirane.entities.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import filev.example.diplomirane.entities.models.ZadanieEntity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrepodavatelDto {

    private Long id;

    private String name;

    private String dlujnost;

    private List<ZadanieEntity> zadaniq;
}
