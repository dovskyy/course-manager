package pl.dovskyy.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dovskyy.studentmanager.model.Grade;
import pl.dovskyy.studentmanager.model.Student;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
