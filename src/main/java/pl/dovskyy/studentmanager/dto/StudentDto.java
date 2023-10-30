package pl.dovskyy.studentmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dovskyy.studentmanager.model.Student;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dob;

    public StudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.age = student.getAge();
        this.dob = student.getDob();
    }
}
