package pl.dovskyy.studentmanager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.repository.StudentRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


//annotation can also be @Component for this class, but you should use @Service to be more precise and make the class more readable for devs
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("Student with given email already exists");
        } else {
            studentRepository.save(student);
        }

    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalArgumentException("student of given ID does not exist");
        } else {
            studentRepository.deleteById(studentId);
        }
    }

    // because we use the @Transactional annotation we don't need to use studentRepository. Eve
    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with given ID doesn't exist"));

        // check if names differ
        if (name != null && name.length() > 0 && !student.getName().equals(name)) {
            student.setName(name);
        }

        // check if emails differ, and if given email doesn't already exist in database
        if (email != null && email.length() > 0 && !student.getEmail().equals(email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalArgumentException("Student with given email already exists");
            } else {
                student.setEmail(email);
            }
        }
    }
}
