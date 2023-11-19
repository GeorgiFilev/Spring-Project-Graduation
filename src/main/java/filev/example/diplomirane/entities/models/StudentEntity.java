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
@Table(name = "studenti")
@JsonIgnoreProperties(ignoreUnknown = true)

public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    private Long id;

    private String name;

    private String fNumber;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("student")
    private List<ZadanieEntity> zadaniq;

    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("student")
    private DiplomnaZashtitaEntity diplomnaZashtita;
}
