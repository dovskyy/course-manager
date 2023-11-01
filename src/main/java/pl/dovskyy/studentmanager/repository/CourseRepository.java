package pl.dovskyy.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dovskyy.studentmanager.model.Course;
import pl.dovskyy.studentmanager.model.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByTeacherId(Long teacherId);

    List<Course> findAllByStudentsId(Long studentId);

    Optional<Course> findCourseByName(String name);


    Optional<Course> findCourseById(Long id);
}
