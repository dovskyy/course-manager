package pl.dovskyy.studentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dovskyy.studentmanager.dto.TeacherDto;
import pl.dovskyy.studentmanager.service.TeacherService;

import java.util.List;

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
    public ResponseEntity<?> getTeacherDtoById(@RequestParam Long teacherId) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherDtoById(teacherId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getTeacherByEmail")
    public ResponseEntity<?> getTeacherDtoByEmail(@RequestParam String teacherEmail) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherDtoByEmail(teacherEmail));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
