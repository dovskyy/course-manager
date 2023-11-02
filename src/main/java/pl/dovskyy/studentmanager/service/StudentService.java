package pl.dovskyy.studentmanager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dovskyy.studentmanager.dto.StudentDto;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.repository.StudentRepository;


import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with given ID doesn't exist"));
    }

    // all the actions will be performed as one transaction. If an exception occurs the transaction will cancel
    @Transactional
    public void updateStudent(Student studentToUpdate){
        String email = studentToUpdate.getEmail();
        if (studentRepository.findStudentByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with given email exists");
        } else {
            studentRepository.save(studentToUpdate);
        }
    }

    public StudentDto getStudentDtoById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with given ID doesn't exist"));
        return new StudentDto(student);
    }

    public List<StudentDto> getStudentsDto() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : students) {
            studentDtoList.add(new StudentDto(student));
        }
        return studentDtoList;
    }

    public StudentDto addNewStudentDto(StudentDto studentDto) {
        //check if student with given email exists
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentDto.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("Student with given email already exists");
        }
        //if not, create new student
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setDob(studentDto.getDob());
        studentRepository.save(student);
        return new StudentDto(student);
    }


    public StudentDto updateStudentDto(Long id, StudentDto studentDto) {
        //check if student with given ID exists
        Optional<Student> studentOptional = studentRepository.findStudentById(id);
        if (studentOptional.isEmpty()){
            throw new IllegalArgumentException("Student with given ID doesn't exist");
        }

        Student student = studentOptional.get();

        if (studentDto.getDob() != null) {
            student.setDob(studentDto.getDob());
        }

        if (studentDto.getName() != null) {
            student.setName(studentDto.getName());
        }

        if (studentDto.getEmail() != null) {
            Optional<Student> studentOptional2 = studentRepository.findStudentByEmail(studentDto.getEmail());
            if (studentOptional2.isPresent()) {
                throw new IllegalArgumentException("Student with given email already exists");
            }
            student.setEmail(studentDto.getEmail());
        }

        studentRepository.save(student);
        return new StudentDto(student);
    }

    public List<StudentDto> getStudentsThatAttendCourses() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();

        students.stream()
                .filter(student -> student.getCourses().size() > 0)
                .map(student -> new StudentDto(student))
                .forEach(studentDtoList::add);

        return studentDtoList;
    }

    public List<StudentDto> getStudentsThatDontAttendCourses() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();

        students.stream()
                .filter(student -> student.getCourses().size() == 0)
                .map(student -> new StudentDto(student))
                .forEach(studentDtoList::add);

        return studentDtoList;
    }
}
