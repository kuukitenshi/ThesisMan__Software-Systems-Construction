package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.StudentCatalog;
import pt.ul.fc.css.thesisman.business.services.dtos.StudentDTO;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

  @Autowired private StudentCatalog studentCatalog;

  public List<StudentDTO> getAllStudentsWithoutThesis() {
    return this.studentCatalog.getAllStudentsWithoutThesis().stream().map(StudentDTO::fromStudent).toList();
  }

  public Optional<StudentDTO> getById(Long studentId) {
    return this.studentCatalog.getById(studentId).map(StudentDTO::fromStudent);
  }

}
