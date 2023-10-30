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

    @GetMapping("/showUpdateForm")
    public ModelAndView updateStudent(@RequestParam Long studentId){
        ModelAndView mav = new ModelAndView("update-student-form");
        Student student = studentService.getStudentById(studentId);
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student) {
        studentService.updateStudent(student);
        return "redirect:/students/list";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.addNewStudent(student);
        return "redirect:/students/list";
    }

    // example path for DELETE would be:

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/students/list";
    }
}