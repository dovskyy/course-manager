package pl.dovskyy.studentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.dovskyy.studentmanager.dto.TeacherDto;
import pl.dovskyy.studentmanager.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherApiController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeachers")
    public ResponseEntity<?> getTeachersDto() {
        return ResponseEntity.ok(teacherService.getTeachersDto());
    }

    @GetMapping("/getTeacherById")
    public ResponseEntity<?> getTeacherDtoById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherDtoById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getTeacherByEmail")
    public ResponseEntity<?> getTeacherDtoByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherDtoByEmail(email));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @PostMapping("/addTeacher")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherDto teacherDto) {
        try {
            return ResponseEntity.ok(teacherService.addNewTeacherDto(teacherDto)); //method will return added teacher with ID
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //or error message
        }
    }

    
    @Transactional
    @DeleteMapping("/deleteTeacher")
    public ResponseEntity<?> deleteTeacher(@RequestParam Long teacherId) {
        try {
            teacherService.deleteTeacher(teacherId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/updateTeacher")
    public ResponseEntity<?> updateTeacher(@RequestParam Long id, @RequestBody TeacherDto teacherDto) {
        try {
            return ResponseEntity.ok(teacherService.updateTeacherDto(id, teacherDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
