package filev.example.diplomirane.entities.Dto;

import filev.example.diplomirane.entities.models.PrepodavatelEntity;
import filev.example.diplomirane.entities.models.StudentEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZadanieDto {

    private Long id;

    private String tema;

    private String purpose;

    private String zadachi;

    private String technologii;


    private StudentEntity student;

    private PrepodavatelEntity prepodavatel;
}
