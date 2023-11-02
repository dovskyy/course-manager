package pl.dovskyy.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.model.Teacher;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentById(Long id);
}
