package pl.dovskyy.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dovskyy.studentmanager.model.Teacher;
import pl.dovskyy.studentmanager.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    public void addNewTeacher (Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherByEmail(teacher.getEmail());
        if (teacherOptional.isPresent()) {
            throw new IllegalArgumentException("Teacher with given email already exists");
        } else {
            teacherRepository.save(teacher);
        }
    }

    public void deleteTeacher (Long teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new IllegalArgumentException("Teacher with given ID doesn't exist");
        } else {
            teacherRepository.deleteById(teacherId);
        }
    }


}
