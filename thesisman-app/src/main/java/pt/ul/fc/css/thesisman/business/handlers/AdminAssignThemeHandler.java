package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.entities.Student;
import pt.ul.fc.css.thesisman.business.entities.Theme;
import pt.ul.fc.css.thesisman.business.entities.Thesis;
import pt.ul.fc.css.thesisman.business.enums.ThemeState;
import pt.ul.fc.css.thesisman.business.exceptions.StudentAlreadyHasThesisException;
import pt.ul.fc.css.thesisman.business.exceptions.StudentNotFoundException;
import pt.ul.fc.css.thesisman.business.exceptions.ThemeNotFoundException;
import pt.ul.fc.css.thesisman.business.exceptions.ThemeTakenException;
import pt.ul.fc.css.thesisman.business.repositories.StudentRepository;
import pt.ul.fc.css.thesisman.business.repositories.ThemeRepository;
import pt.ul.fc.css.thesisman.business.repositories.ThesisRepository;

import java.util.Optional;

@Component
public class AdminAssignThemeHandler {

  @Autowired private ThemeRepository themeRepository;
  @Autowired private StudentRepository studentRepository;
  @Autowired private ThesisRepository thesisRepository;

  @Transactional
  public void assignTheme(Long themeId, Long studentId) throws ThemeNotFoundException, StudentNotFoundException, StudentAlreadyHasThesisException, ThemeTakenException {
    Optional<Theme> themeOpt = this.themeRepository.findById(themeId);
    if (themeOpt.isEmpty()) {
      throw new ThemeNotFoundException();
    }
    Theme theme = themeOpt.get();
    Optional<Student> studentOpt = this.studentRepository.findById(studentId);
    if (studentOpt.isEmpty()) {
      throw new StudentNotFoundException();
    }
    if (theme.getState() == ThemeState.TAKEN) {
      throw new ThemeTakenException();
    }
    Student student = studentOpt.get();
    Optional<Thesis> thesisOpt = this.thesisRepository.findByStudent_Id(studentId);
    if (thesisOpt.isPresent()) {
      throw new StudentAlreadyHasThesisException();
    }
    Thesis thesis = new Thesis(student, theme);
    this.thesisRepository.save(thesis);
    theme.setState(ThemeState.TAKEN);
    this.themeRepository.save(theme);
  }
}
