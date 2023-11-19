package filev.example.diplomirane.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "diplomni_zashtiti")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiplomnaZashtitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diplomna_zashtita_id_seq")
    private Long id;

    private Date dateUploaded;

    private String mark;

    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
