package pl.dovskyy.studentmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.dovskyy.studentmanager.model.Course;

@NoArgsConstructor
@Data
public class CourseDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String name;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int numberOfStudents;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String teacherName;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String teacherEmail;

    private Long teacherId;

    public CourseDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.numberOfStudents = course.getNumberOfStudents();
        this.teacherName = course.getTeacher().getName();
        this.teacherEmail = course.getTeacher().getEmail();
        this.teacherId = course.getTeacher().getId();
    }

    public CourseDto(String name, Long teacherId) {
        this.name = name;
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
