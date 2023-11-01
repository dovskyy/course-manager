package pl.dovskyy.studentmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getStudentsTest() {
        //given
        int randomInt = (int) (Math.random() * 100);
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < randomInt; i++) {
            Student student = new Student();
            student.setName("Student" + i);
            student.setEmail("student" + i + "@gmail.com");
            student.setId((long) i);
            students.add(student);
        }

        //when
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> result = studentService.getStudents();

        //then
        assertEquals(result.size(), randomInt);
    }

    @Test
    void addNewStudentTest() {
        //given
        int randomInt = (int) (Math.random() * 100);
        Student student = new Student();
        student.setName("Student" + randomInt);
        student.setEmail("student" + randomInt + "@gmail.com");
        student.setId((long) randomInt);

        //when
        when(studentRepository.save(student)).thenReturn(student);
        studentService.addNewStudent(student);

        //then
        assertEquals(studentRepository.save(student), student);
    }

    @Test
    void deleteStudent() {
        //given
        Student student = new Student();
        student.setEmail("mail@test.com");

        //when
        when(studentRepository.findStudentByEmail(student.getEmail())).thenReturn(Optional.empty());
        when(studentRepository.existsById(student.getId())).thenReturn(true);
        studentService.addNewStudent(student);
        studentService.deleteStudent(student.getId());
        when(studentRepository.existsById(student.getId())).thenReturn(false);

        //then
        assertFalse(studentRepository.existsById(student.getId()));
    }

    @Test
    void getStudentByIdTest() {
        //given
        int randomInt = (int) (Math.random() * 100);
        Student student = new Student();
        student.setName("Student" + randomInt);
        student.setEmail("student" + randomInt + "@gmail.com");
        student.setId((long) randomInt);

        //when
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Student result = studentService.getStudentById((long) randomInt);

        //then
        assertEquals(result.getId(), student.getId());
    }

    @Test
    void updateStudentTest() {
        //given
        int randomInt = (int) (Math.random() * 100);
        Student student = new Student();
        student.setName("Student" + randomInt);
        student.setEmail("student" + randomInt + "@gmail.com");
        student.setId((long) randomInt);

        //when
        when(studentRepository.save(student)).thenReturn(student);
        studentService.updateStudent(student);

        //then
        assertEquals(studentRepository.save(student), student);
    }
}