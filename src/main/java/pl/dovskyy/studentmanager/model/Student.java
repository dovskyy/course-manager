package pl.dovskyy.studentmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    public Student() {
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    //Spring is automatically adding an "age" field to the Student JSON object in GET response
    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    //this one as well
    public String getMood() {
        return "good";
    }
}