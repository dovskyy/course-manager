package pl.dovskyy.studentmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dovskyy.studentmanager.model.Grade;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GradeDto {

    private Long id;

    private LocalDate date;
    private int gradeValue;
    private Long courseId;
    private Long studentId;

    public GradeDto(Grade grade) {
        this.id = grade.getId();
        this.date = grade.getDate();
        this.gradeValue = grade.getGradeValue();
        this.courseId = grade.getCourse().getId();
        this.studentId = grade.getStudent().getId();
    }
}
