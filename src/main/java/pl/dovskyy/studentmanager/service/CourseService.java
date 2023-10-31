package pl.dovskyy.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dovskyy.studentmanager.dto.CourseDto;
import pl.dovskyy.studentmanager.dto.StudentDto;
import pl.dovskyy.studentmanager.model.Course;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.repository.CourseRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<Student> getStudentsFromCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with given ID doesn't exist"));

        return new ArrayList<>(course.getStudents());
    }

    public List<StudentDto> getStudentsDtoFromCourse(Long courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with given ID doesn't exist"));
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : course.getStudents()) {
            studentDtoList.add(new StudentDto(student));
        }
        return studentDtoList;
    }

    public List<CourseDto> getCoursesDto() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> coursesDto = new ArrayList<>();
        for (Course course : courses) {
            coursesDto.add(new CourseDto(course));
        }
        return coursesDto;
    }

    public CourseDto getCourseDtoById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with given ID doesn't exist"));
        return new CourseDto(course);
    }

    public List<CourseDto> getCoursesDtoByTeacherId(Long teacherId) {
        List<Course> courses = courseRepository.findAllByTeacherId(teacherId);
        List<CourseDto> coursesDto = new ArrayList<>();
        for (Course course : courses) {
            coursesDto.add(new CourseDto(course));
        }
        return coursesDto;
    }

    public List<CourseDto> getCoursesDtoByStudentId(Long studentId) {
        List<Course> courses = courseRepository.findAllByStudentsId(studentId);
        List<CourseDto> coursesDto = new ArrayList<>();
        for (Course course : courses) {
            coursesDto.add(new CourseDto(course));
        }
        return coursesDto;
    }

    public CourseDto addNewCourseDto(CourseDto courseDto) {
        //check whether Course with given name exists
        Optional<Course> courseOptional = courseRepository.findCourseByName(courseDto.getName());
        if (courseOptional.isPresent()) {
            throw new IllegalArgumentException("Course with given name already exists");
        }
        //if not, create new course
        Course course = new Course();
        course.setName(courseDto.getName());
        courseRepository.save(course);
        return new CourseDto(course);
    }
}
