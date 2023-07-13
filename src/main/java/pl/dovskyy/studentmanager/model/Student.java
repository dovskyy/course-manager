package pl.dovskyy.studentmanager.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();



    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }


    public String getMood() {
        return "good";
    }
}
