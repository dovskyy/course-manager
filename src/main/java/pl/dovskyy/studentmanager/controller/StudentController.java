package pl.dovskyy.studentmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.service.StudentService;


import java.util.List;

@Controller
@RequestMapping(path = "students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public ModelAndView getStudents(){
        ModelAndView mav = new ModelAndView("list-students"); //list-students is the thymeleaf template name
        List<Student> studentList = studentService.getStudents();
        mav.addObject("students", studentList);
        return mav;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm() {
        ModelAndView mav = new ModelAndView("add-student-form");
        Student newStudent = new Student();
        mav.addObject("student", newStudent);
        return mav;
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.addNewStudent(student);
        return "redirect:/students/list";
    }


    // example path for DELETE would be:
    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/update/{studentId}")
    public void updateStudent(@PathVariable Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {

        studentService.updateStudent(studentId, name, email);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMsg", ex.getMessage());
        return mav;
    }
}
