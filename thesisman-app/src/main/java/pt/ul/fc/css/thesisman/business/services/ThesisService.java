package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.ThesisCatalog;
import pt.ul.fc.css.thesisman.business.services.dtos.ThesisDTO;

import java.util.List;
import java.util.Optional;

@Service
public class ThesisService {

  @Autowired private ThesisCatalog thesisCatalog;

  public List<ThesisDTO> getThesisFromTeacher(Long teacherId) {
    return this.thesisCatalog.getFromTeacher(teacherId).stream().map(ThesisDTO::fromThesis).toList();
  }

  public Optional<ThesisDTO> getById(Long thesisId) {
    return this.thesisCatalog.getById(thesisId).map(ThesisDTO::fromThesis);
  }

  public Optional<ThesisDTO> getBySubmissionId(Long submissionId) {
    return this.thesisCatalog.getBySubmissionId(submissionId).map(ThesisDTO::fromThesis);
  }

  public Optional<ThesisDTO> getThesisFromStudent(Long studentId) {
    return this.thesisCatalog.getFromStudent(studentId).map(ThesisDTO::fromThesis);
  }
}
