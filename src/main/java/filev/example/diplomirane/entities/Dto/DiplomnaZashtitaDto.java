package filev.example.diplomirane.entities.Dto;

import filev.example.diplomirane.entities.models.StudentEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiplomnaZashtitaDto {

    private Long id;

    private Date dateUploaded;

    private String mark;

    private StudentEntity student;
}
