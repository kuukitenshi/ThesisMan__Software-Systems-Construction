package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Application;
import pt.ul.fc.css.thesisman.business.repositories.ApplicationRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ApplicationCatalog {

  @Autowired private ApplicationRepository applicationRepository;

  public List<Application> listApplicationsByStudent(Long studentId) {
    return this.applicationRepository.findByStudent_Id(studentId);
  }

  public Optional<Application> getById(Long applicationId) {
    return this.applicationRepository.findById(applicationId);
  }
}
