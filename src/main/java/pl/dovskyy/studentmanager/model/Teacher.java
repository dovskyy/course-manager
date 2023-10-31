package pl.dovskyy.studentmanager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"courses"})
@EqualsAndHashCode(exclude = {"courses"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>();

    public int getNumberOfCourses() {
        return courses.size();
    }
}
