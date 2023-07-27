package pl.dovskyy.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dovskyy.studentmanager.model.Course;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.repository.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.getById(courseId);
    }

    public void addNewCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("Course with given ID doesn't exist");
        } else {
            courseRepository.deleteById(courseId);
        }
    }

    @Transactional
    public void addStudentToCourse(Course courseToUpdate, Student student){
        Optional<Course> course = courseRepository.findById(courseToUpdate.getId());
        if (course.isPresent()){
            Course c = course.get();
            c.getStudents().add(student);
            courseRepository.save(c);
        } else {
            throw new IllegalArgumentException("No course found");
        }
    }
}
