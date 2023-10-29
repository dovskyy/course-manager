package pl.dovskyy.studentmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.dovskyy.studentmanager.model.Course;
import pl.dovskyy.studentmanager.model.Teacher;
import pl.dovskyy.studentmanager.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    public void getCoursesTest() {

        //given
        int randomInt = (int) (Math.random() * 100);
        List<Course> courses = new ArrayList<>();

        for (int i = 0; i < randomInt; i++) {
            Course course = new Course();
            Teacher teacher = new Teacher();
            course.setTeacher(teacher);
            courses.add(course);
        }

        //when
        when(courseRepository.findAll()).thenReturn(courses);
        List<Course> result = courseService.getCourses();

        //then
        assertEquals(result.size(), randomInt);
        verify(courseRepository, times(1)).findAll();

    }

    @Test
    public void getCourseByIdTest() {
        //given
        int randomInt = (int) (Math.random() * 100);
        Course course = new Course();
        course.setId((long) randomInt);

        //when
        when(courseRepository.getById(course.getId())).thenReturn(course);
        Course result = courseService.getCourseById((long) randomInt);

        //then
        assertEquals(result.getId(), course.getId());
        verify(courseRepository, times(1)).getById(course.getId());
    }

    @Test
    public void addNewCourseTest() {

        //given
        Course course = new Course();

        //when
        courseService.addNewCourse(course);

        //then
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void deleteCourseTest() {

        //given
        int randomInt = (int) (Math.random() * 100);
        Course course = new Course();
        course.setId((long) randomInt);

        //when
        when(courseRepository.existsById(course.getId())).thenReturn(true);
        courseService.deleteCourse(course.getId());

        //then
        verify(courseRepository, times(1)).deleteById(course.getId());
    }
}