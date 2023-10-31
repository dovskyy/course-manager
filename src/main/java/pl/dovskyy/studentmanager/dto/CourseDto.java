package pl.dovskyy.studentmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dovskyy.studentmanager.model.Course;

@NoArgsConstructor
@Data
public class CourseDto {
    private Long id;
    private String name;
    private int numberOfStudents;
    private String teacherName;
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

}
