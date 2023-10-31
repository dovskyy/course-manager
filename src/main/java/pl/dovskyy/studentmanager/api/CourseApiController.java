package pl.dovskyy.studentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dovskyy.studentmanager.dto.CourseDto;
import pl.dovskyy.studentmanager.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseApiController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getCourses")
    public ResponseEntity<?> getCoursesDto() {
           return ResponseEntity.ok(courseService.getCoursesDto());
    }

    @GetMapping("/getCourseById")
    public ResponseEntity<?> getCourseDtoById(Long id) {
        try {
            return ResponseEntity.ok(courseService.getCourseDtoById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getCoursesByTeacherId")
    public ResponseEntity<?> getCoursesDtoByTeacherId(Long id) {
        try {
            return ResponseEntity.ok(courseService.getCoursesDtoByTeacherId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
