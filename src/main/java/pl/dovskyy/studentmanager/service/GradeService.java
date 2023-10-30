package pl.dovskyy.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dovskyy.studentmanager.model.Grade;
import pl.dovskyy.studentmanager.model.Student;
import pl.dovskyy.studentmanager.repository.GradeRepository;

import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    public void deleteGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }

}
