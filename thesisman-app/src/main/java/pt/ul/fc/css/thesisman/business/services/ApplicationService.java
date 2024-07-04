package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.ApplicationCatalog;
import pt.ul.fc.css.thesisman.business.entities.Application;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.handlers.AddApplicationHandler;
import pt.ul.fc.css.thesisman.business.handlers.CancelApplicationHandler;
import pt.ul.fc.css.thesisman.business.services.dtos.ApplicationDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.IncompatiblityException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;
import pt.ul.fc.css.thesisman.business.services.exceptions.StudentMaxApplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

  @Autowired private AddApplicationHandler addApplicationsHandler;
  @Autowired private CancelApplicationHandler cancelApplicationHandler;
  @Autowired private ApplicationCatalog applicationCatalog;

  public List<ApplicationDTO> studentApplicationsList(Long studentId) throws NotFoundException {
    List<Application> applications = applicationCatalog.listApplicationsByStudent(studentId);
    List<ApplicationDTO> applicationDTOS = new ArrayList<>();
    for (Application application : applications) {
      applicationDTOS.add(ApplicationDTO.fromApplication(application));
    }
    if(applicationDTOS.isEmpty())
      throw new NotFoundException("No applications found for student with id " + studentId + "!");
    return applicationDTOS;
  }

  public ApplicationDTO addApplication(Long themeId, Long studentId) throws NotFoundException, StudentMaxApplicationException, AlreadyExistsException, IncompatiblityException {
    try {
      Application application = addApplicationsHandler.addApplication(themeId, studentId);
      return ApplicationDTO.fromApplication(application);
    } catch (ThemeNotFoundException e) {
      throw new NotFoundException("Theme with id " + themeId + " was not found!", e);
    } catch (StudentNotFoundException e) {
      throw new NotFoundException("Student with id " + studentId + " was not found!", e);
    } catch (MaxApplicationsExceedException e) {
      throw new StudentMaxApplicationException("You cannot apply to more than 5 themes!", e);
    } catch (AlreadyHasApplicationException e) {
      throw new AlreadyExistsException("The is already an application for that theme!", e);
    } catch (IncompatibleMastersException e) {
      throw new IncompatiblityException("That theme does not support your masters degree!", e);
    }
  }

  public void cancelApplication(Long applicationId) throws NotFoundException {
    try {
      this.cancelApplicationHandler.cancelApplication(applicationId);
    } catch (ApplicationNotFoundException e) {
      throw new NotFoundException("Application with id " + applicationId + " was not found!");
    }
  }

  public Optional<ApplicationDTO> getById(Long applicationId) {
    return this.applicationCatalog.getById(applicationId).map(ApplicationDTO::fromApplication);
  }
}
