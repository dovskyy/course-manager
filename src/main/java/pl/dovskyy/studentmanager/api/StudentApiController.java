package pl.dovskyy.studentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.dovskyy.studentmanager.dto.StudentDto;
import pl.dovskyy.studentmanager.service.CourseService;
import pl.dovskyy.studentmanager.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {


    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentApiController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

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
    public ResponseEntity<?> getStudentsDtoFromCourse(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(courseService.getStudentsDtoFromCourse(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(@RequestParam Long id, @RequestBody StudentDto studentDto) {
        try {
            return ResponseEntity.ok(studentService.updateStudentDto(id, studentDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto) {
        try {
            return ResponseEntity.ok(studentService.addNewStudentDto(studentDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getStudentsWithNoCourse")
    public ResponseEntity<?> getStudentsWithNoCourse() {
        try {
            return ResponseEntity.ok(studentService.getStudentsThatDontAttendCourses());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getStudentsWithCourse")
    public ResponseEntity<?> getStudentsWithCourse() {
        try {
            return ResponseEntity.ok(studentService.getStudentsThatAttendCourses());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<?> deleteStudent(@RequestParam Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
