package filev.example.diplomirane.entities.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "zadaniq")
@JsonIgnoreProperties(ignoreUnknown = true)

public class ZadanieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zadanie_id_seq")
    private Long id;

    private String tema;

    private String purpose;

    private String zadachi;

    private String technologii;

    private String odobreno ;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "prepodavatel_id")
    private PrepodavatelEntity prepodavatel;
}
