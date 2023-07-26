package pl.dovskyy.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.dovskyy.studentmanager.model.Course;
import pl.dovskyy.studentmanager.model.Teacher;
import pl.dovskyy.studentmanager.service.CourseService;
import pl.dovskyy.studentmanager.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping(path = "courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    @Autowired
    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping("/list")
    public ModelAndView getCourses(){
        ModelAndView mav = new ModelAndView("list-courses");
        List<Course> courseList = courseService.getCourses();
        mav.addObject("courses", courseList);
        return mav;
    }

    @GetMapping("/addCourseForm")
    public ModelAndView addCourseForm() {
        ModelAndView mav = new ModelAndView("add-course-form");
        Course course = new Course();
        List<Teacher> teacherList = teacherService.getTeachers();
        mav.addObject("course", course);
        mav.addObject("teachers", teacherList);
        return mav;
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute Course course){
        courseService.addNewCourse(course);
        return "redirect:/courses/list";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam Long courseId){
        courseService.deleteCourse(courseId);
        return "redirect:/courses/list";
    }
}
