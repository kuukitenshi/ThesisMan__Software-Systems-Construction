package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.entities.Application;
import pt.ul.fc.css.thesisman.business.entities.Student;
import pt.ul.fc.css.thesisman.business.entities.Theme;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.repositories.ApplicationRepository;
import pt.ul.fc.css.thesisman.business.repositories.StudentRepository;
import pt.ul.fc.css.thesisman.business.repositories.ThemeRepository;

import java.util.Optional;

@Component
public class AddApplicationHandler {

  @Autowired private ApplicationRepository applicationRepository;
  @Autowired private ThemeRepository themeRepository;
  @Autowired private StudentRepository studentRepository;

  @Transactional
  public Application addApplication(Long themeId, Long studentId) throws ThemeNotFoundException, StudentNotFoundException, MaxApplicationsExceedException, AlreadyHasApplicationException, IncompatibleMastersException {
    Optional<Theme> themeOpt = this.themeRepository.findById(themeId);
    if (themeOpt.isEmpty()) {
      throw new ThemeNotFoundException();
    }
    Theme theme = themeOpt.get();
    Optional<Student> studentOpt = this.studentRepository.findById(studentId);
    if (studentOpt.isEmpty()) {
      throw new StudentNotFoundException();
    }
    Student student = studentOpt.get();
    if (theme.getCompatibleMasters().stream().noneMatch(m -> m.getId().equals(student.getMastersDegree().getId()))) {
      throw new IncompatibleMastersException();
    }
    Long count = this.applicationRepository.countByStudent_Id(studentId);
    if (count >= 5) {
      throw new MaxApplicationsExceedException();
    }
    Optional<Application> other = this.applicationRepository.findByStudent_IdAndTheme_Id(studentId, themeId);
    if (other.isPresent()) {
      throw new AlreadyHasApplicationException();
    }
    Application application = new Application(theme, student);
    this.applicationRepository.save(application);
    return application;
  }
}
