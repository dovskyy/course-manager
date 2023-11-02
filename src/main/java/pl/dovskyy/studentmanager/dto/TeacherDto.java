package pl.dovskyy.studentmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dovskyy.studentmanager.model.Teacher;

@NoArgsConstructor
@Data
public class TeacherDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY) //this field will be read only by Swagger
    private Long id;
    private String name;
    private String email;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int numberOfCourses;

    public TeacherDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.numberOfCourses = teacher.getNumberOfCourses();
    }
}
