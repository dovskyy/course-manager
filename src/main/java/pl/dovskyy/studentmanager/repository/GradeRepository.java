package pl.dovskyy.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dovskyy.studentmanager.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
