package pl.dovskyy.studentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.dovskyy.studentmanager.dto.CourseDto;
import pl.dovskyy.studentmanager.service.CourseService;

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

    @Transactional
    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody CourseDto courseDto) {
        try {
            CourseDto courseDtoToAdd = new CourseDto(courseDto.getName(), courseDto.getTeacherId());
            return ResponseEntity.ok(courseService.addNewCourseDto(courseDtoToAdd)); //method will return added course with ID
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //or error message
        }
    }

    @Transactional
    @DeleteMapping("/deleteCourse")
    public ResponseEntity<?> deleteCourse(@RequestParam Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<?> updateCourse(@RequestParam Long id, @RequestBody CourseDto courseDto) {
        try {
            return ResponseEntity.ok(courseService.updateCourseDto(id, courseDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
