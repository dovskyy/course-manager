package pl.dovskyy.studentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dovskyy.studentmanager.service.CourseService;
import pl.dovskyy.studentmanager.service.StudentService;

@RestController()
@RequestMapping("/api/students")
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/getStudents")
    public ResponseEntity<?> getStudentsDto() {
        return ResponseEntity.ok(studentService.getStudentsDto());
    }

    @GetMapping("/getStudentById")
    public ResponseEntity<?> getStudentDtoById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(studentService.getStudentDtoById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getStudentsFromCourse")
    public ResponseEntity<?> getStudentsDtoFromCourse(@RequestParam Long courseId) {
        try {
            return ResponseEntity.ok(courseService.getStudentsDtoFromCourse(courseId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
