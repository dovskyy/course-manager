package pl.dovskyy.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.dovskyy.studentmanager.model.Teacher;
import pl.dovskyy.studentmanager.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping(path = "teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/list")
    public ModelAndView getTeachers(){
        ModelAndView mav = new ModelAndView("list-teachers");
        List<Teacher> teacherList = teacherService.getTeachers();
        mav.addObject("teachers", teacherList);
        return mav;
    }

    @GetMapping("/addTeacherForm")
    public ModelAndView addTeacherForm() {
        ModelAndView mav = new ModelAndView("add-teacher-form");
        Teacher teacher = new Teacher();
        mav.addObject("teacher", teacher);
        return mav;
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher (@ModelAttribute Teacher teacher){
        teacherService.addNewTeacher(teacher);
        return "redirect:/teachers/list";
    }

    @GetMapping("/deleteTeacher")
    public String deleteTeacher (@RequestParam Long teacherId){
        teacherService.deleteTeacher(teacherId);
        return "redirect:/teachers/list";
    }
}
