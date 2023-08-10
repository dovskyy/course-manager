package pl.dovskyy.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.dovskyy.studentmanager.model.Course;
import pl.dovskyy.studentmanager.model.Grade;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.service.CourseService;
import pl.dovskyy.studentmanager.service.GradeService;
import pl.dovskyy.studentmanager.service.StudentService;

import java.util.List;

@Controller
@RequestMapping(path = "grades")
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public GradeController(GradeService gradeService, StudentService studentService, CourseService courseService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public ModelAndView listAllGrades(){
        ModelAndView mav = new ModelAndView("list-grades");
        List<Grade> gradeList = gradeService.getGrades();
        mav.addObject("grades", gradeList);
        return mav;
    }

    @GetMapping("/addGradeForm")
    public ModelAndView addGradeForm(){
        ModelAndView mav = new ModelAndView("add-grade-form");
        Grade grade = new Grade();
        List<Student> studentList = studentService.getStudents();
        List<Course> courseList = courseService.getCourses();
        mav.addObject(grade);
        mav.addObject(studentList);
        mav.addObject(courseList);

        return mav;
    }

    @PostMapping("/saveGrade")
    public String saveGrade(@ModelAttribute Grade grade){
        gradeService.saveGrade(grade);
        return "redirect:/grades/list";
    }
}
