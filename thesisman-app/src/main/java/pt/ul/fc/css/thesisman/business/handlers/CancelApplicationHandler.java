package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Application;
import pt.ul.fc.css.thesisman.business.exceptions.ApplicationNotFoundException;
import pt.ul.fc.css.thesisman.business.repositories.ApplicationRepository;

import java.util.Optional;

@Component
public class CancelApplicationHandler {

  @Autowired private ApplicationRepository applicationRepository;

  public void cancelApplication(Long applicationId) throws ApplicationNotFoundException {
    Optional<Application> applicationOpt = this.applicationRepository.findById(applicationId);
    if (applicationOpt.isEmpty()) {
      throw new ApplicationNotFoundException();
    }
    Application application = applicationOpt.get();
    this.applicationRepository.delete(application);
  }
}
