package pl.dovskyy.studentmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.dovskyy.studentmanager.model.Teacher;
import pl.dovskyy.studentmanager.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    void getTeachersTest() {
        //given
        int randomInt = (int) (Math.random() * 100);
        List<Teacher> teachers = new ArrayList<>();

        for (int i = 0; i < randomInt; i++) {
            Teacher teacher = new Teacher();
            teacher.setName("Teacher" + i);
            teacher.setEmail("teacher" + i + "@gmail.com");
            teacher.setId((long) i);
            teachers.add(teacher);
        }

        //when
        when(teacherRepository.findAll()).thenReturn(teachers);
        List<Teacher> result = teacherService.getTeachers();

        //then
        assertEquals(result.size(), randomInt);

    }
}