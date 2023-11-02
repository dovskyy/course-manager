package pl.dovskyy.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dovskyy.studentmanager.dto.TeacherDto;
import pl.dovskyy.studentmanager.model.Teacher;
import pl.dovskyy.studentmanager.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public void addNewTeacher(Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherByEmail(teacher.getEmail());
        if (teacherOptional.isPresent()) {
            throw new IllegalArgumentException("Teacher with given email already exists");
        } else {
            teacherRepository.save(teacher);
        }
    }

    public void deleteTeacher(Long teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new IllegalArgumentException("Teacher with given ID doesn't exist");
        } else {
            try {
                teacherRepository.deleteById(teacherId);
            } catch (Exception e) {
                throw new IllegalArgumentException("Teacher has courses assigned. Delete them first.");
            }
        }
    }


    public List<TeacherDto> getTeachersDto() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teachersDto = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teachersDto.add(new TeacherDto(teacher));
        }
        return teachersDto;
    }

    public TeacherDto getTeacherDtoById(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with given ID doesn't exist"));
        return new TeacherDto(teacher);
    }

    public TeacherDto getTeacherDtoByEmail(String teacherEmail) {
        Teacher teacher = teacherRepository.findTeacherByEmail(teacherEmail)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with given email doesn't exist"));
        TeacherDto teacherDto = new TeacherDto(teacher);
        return teacherDto;
    }

    public TeacherDto addNewTeacherDto(TeacherDto teacherDto) {
        //check if teacher with given email exists
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherByEmail(teacherDto.getEmail());
        if (teacherOptional.isPresent()) {
            throw new IllegalArgumentException("Teacher with given email already exists");
        }
        //if not, create new teacher
        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.getName());
        teacher.setEmail(teacherDto.getEmail());
        teacherRepository.save(teacher);
        return new TeacherDto(teacher);
    }

    public TeacherDto updateTeacherDto(Long id, TeacherDto teacherDto) {
        //check if teacher with given id exists
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherById(id);
        if (teacherOptional.isEmpty()) {
            throw new IllegalArgumentException("Teacher with given ID doesn't exist");
        }

        Teacher teacher = teacherOptional.get();
        teacher.setName(teacherDto.getName());
        teacher.setEmail(teacherDto.getEmail());

        //check if email to update is not already taken by another teacher
        Optional<Teacher> teacherOptional2 = teacherRepository.findTeacherByEmail(teacher.getEmail());
        if (teacherOptional2.isPresent()) {
            throw new IllegalArgumentException("Teacher with given email already exists");
        }

        teacherRepository.save(teacher);
        return new TeacherDto(teacher);
    }

}
