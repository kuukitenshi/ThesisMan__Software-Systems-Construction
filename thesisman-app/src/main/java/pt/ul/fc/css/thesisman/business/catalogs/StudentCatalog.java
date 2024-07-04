package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Student;
import pt.ul.fc.css.thesisman.business.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Component
public class StudentCatalog {

  @Autowired private StudentRepository studentRepository;

  public List<Student> getAllStudentsWithoutThesis() {
    return this.studentRepository.findWithoutThesis();
  }

  public Optional<Student> getById(Long studentId) {
    return this.studentRepository.findById(studentId);
  }

}
