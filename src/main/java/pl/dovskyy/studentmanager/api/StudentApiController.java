package pl.dovskyy.studentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dovskyy.studentmanager.dto.StudentDto;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.service.CourseService;
import pl.dovskyy.studentmanager.service.StudentService;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/getStudents")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    //implement methods:
    //getStudentById
    //addNewStudent
    //updateStudent
    //deleteStudent
    //getStudentsFromCourse

    @GetMapping("/getStudentById")
    public StudentDto getStudentDtoById(Long studentId) {
        return studentService.getStudentDtoById(studentId);
    }

    @GetMapping("/getStudentsFromCourse")
    public List<StudentDto> getStudentsDtoFromCourse(@RequestParam Long courseId) {
        return courseService.getStudentDtoFromCourse(courseId);
    }

}
