package filev.example.diplomirane.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "prepodavateli")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrepodavatelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prepodavatel_id_seq")
    private Long id;

    private String name;

    private String dlujnost;

    @OneToMany(mappedBy = "prepodavatel", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("prepodavatel")
    private List<ZadanieEntity> zadaniq;
}
