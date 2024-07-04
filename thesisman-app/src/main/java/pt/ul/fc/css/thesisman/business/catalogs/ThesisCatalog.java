package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Thesis;
import pt.ul.fc.css.thesisman.business.repositories.ThesisRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ThesisCatalog {

  @Autowired private ThesisRepository thesisRepository;

  public Optional<Thesis> getBySubmissionId(Long submissionId) {
    return this.thesisRepository.findThesisBySubmissionId(submissionId);
  }

  public Optional<Thesis> getById(Long thesisId) {
    return this.thesisRepository.findById(thesisId);
  }

  public List<Thesis> getFromTeacher(Long teacherId) {
    return this.thesisRepository.findByTheme_Advisor_Id(teacherId);
  }

  public Optional<Thesis> getFromStudent(Long studentId) {
    return this.thesisRepository.findByStudent_Id(studentId);
  }

}
