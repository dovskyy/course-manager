package pl.dovskyy.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dovskyy.studentmanager.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
