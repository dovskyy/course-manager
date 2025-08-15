package pl.dovskyy.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.dovskyy.studentmanager.model.Course;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.model.Teacher;
import pl.dovskyy.studentmanager.service.CourseService;
import pl.dovskyy.studentmanager.service.StudentService;
import pl.dovskyy.studentmanager.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping(path = "courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public ModelAndView getCourses() {
        ModelAndView mav = new ModelAndView("list-courses");
        List<Course> courseList = courseService.getCourses();
        mav.addObject("courses", courseList);
        return mav;
    }

    @GetMapping("/listStudents")
    public ModelAndView getStudentsFromCourse(@RequestParam Long courseId){
        ModelAndView mav = new ModelAndView("list-course-students");
        List<Student> studentList = courseService.getStudentsFromCourse(courseId);
        mav.addObject("students", studentList);
        return mav;
    }

    @GetMapping("/addCourseForm")
    public ModelAndView addCourseForm() {
        ModelAndView mav = new ModelAndView("add-course-form");
        Course course = new Course();
        List<Teacher> teacherList = teacherService.getTeachers();

        // Check if there are any teachers available
        if (teacherList.isEmpty()) {
            mav.setViewName("redirect:/teachers/addTeacherForm");
            mav.addObject("error", "Musisz najpierw dodać nauczyciela przed utworzeniem kursu.");
            return mav;
        }

        mav.addObject("course", course);
        mav.addObject("teachers", teacherList);
        return mav;
    }

    @GetMapping("/addStudentCourseForm")
    public ModelAndView addStudentToCourse(@RequestParam Long courseId) {
        ModelAndView mav = new ModelAndView("add-student-course-form");
        Course course = courseService.getCourseById(courseId);
        List<Student> studentList = studentService.getStudents();

        mav.addObject("course", course);
        mav.addObject("studentsList", studentList);

        return mav;
    }

    @PostMapping("/addStudentToCourse")
    public String addStudentToCourse(@ModelAttribute Course course, @RequestParam("studentId") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        courseService.addStudentToCourse(course, student);
        return "redirect:/courses/list";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute Course course) {
        // Validate that a teacher is selected
        if (course.getTeacher() == null || course.getTeacher().getId() == null) {
            return "redirect:/courses/addCourseForm?error=teacher_required";
        }

        // Validate that the course name is not empty
        if (course.getName() == null || course.getName().trim().isEmpty()) {
            return "redirect:/courses/addCourseForm?error=name_required";
        }

        try {
            courseService.addNewCourse(course);
            return "redirect:/courses/list";
        } catch (Exception e) {
            return "redirect:/courses/addCourseForm?error=save_failed";
        }
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam Long courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/courses/list";
    }
}
