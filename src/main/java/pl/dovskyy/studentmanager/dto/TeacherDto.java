package pl.dovskyy.studentmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dovskyy.studentmanager.model.Teacher;

@NoArgsConstructor
@Data
public class TeacherDto {
    private Long id;
    private String name;
    private String email;
    private int numberOfCourses;

    public TeacherDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.numberOfCourses = teacher.getNumberOfCourses();
    }
}
