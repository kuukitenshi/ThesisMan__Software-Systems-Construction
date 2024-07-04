package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.ThemeCatalog;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.handlers.AdminAssignThemeHandler;
import pt.ul.fc.css.thesisman.business.handlers.ListThemesHandler;
import pt.ul.fc.css.thesisman.business.handlers.TeacherSubmitThemeHandler;
import pt.ul.fc.css.thesisman.business.handlers.WorkerSubmitThemeHandler;
import pt.ul.fc.css.thesisman.business.services.dtos.IThemeDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

  @Autowired private AdminAssignThemeHandler adminAssignThemeHandler;
  @Autowired private WorkerSubmitThemeHandler workerSubmitThemeHandler;
  @Autowired private TeacherSubmitThemeHandler teacherSubmitThemeHandler;
  @Autowired private ListThemesHandler listThemesHandler;
  @Autowired private ThemeCatalog themeCatalog;

  public void submitDissertation(String title, String description, long advisorId, float remuneration, List<Long> compatibleMasters) throws NotFoundException, InvalidFieldException {
    try {
      this.teacherSubmitThemeHandler.submitDissertationTheme(title, description, advisorId, remuneration, compatibleMasters);
    } catch (InvalidTitleException e) {
      throw new InvalidFieldException("Invalid title!", e);
    } catch (InvalidDescriptionException e) {
      throw new InvalidFieldException("Invalid description!", e);
    } catch (TeacherNotFoundException e) {
      throw new NotFoundException("Teacher with id " + advisorId + " was not found!", e);
    } catch (InvalidRemunerationException e) {
      throw new InvalidFieldException("Invalid remuneration!", e);
    } catch (MastersDegreeNotFoundException e) {
      throw new NotFoundException("MastersDegree not found!", e);
    }
  }

  public void submitProject(String title, String description, long advisorId, float remuneration, List<Long> compatibleMasters, long workerId) throws NotFoundException, InvalidFieldException {
    try {
      this.workerSubmitThemeHandler.submitProjectTheme(title, description, advisorId, remuneration, compatibleMasters, workerId);
    } catch (InvalidDescriptionException e) {
      throw new InvalidFieldException("Invalid description!", e);
    } catch (InvalidTitleException e) {
      throw new InvalidFieldException("Invalid title!", e);
    } catch (TeacherNotFoundException e) {
      throw new NotFoundException("Teacher with id " + advisorId + " was not found!", e);
    } catch (InvalidRemunerationException e) {
      throw new InvalidFieldException("Invalid remuneration!", e);
    } catch (MastersDegreeNotFoundException e) {
      throw new NotFoundException("MastersDegree not found!", e);
    } catch (WorkerNotFoundException e) {
      throw new NotFoundException("Worker with id " + workerId + " was not found!", e);
    }
  }

  public Optional<IThemeDTO> getById(Long themeId) {
    return this.themeCatalog.getById(themeId).map(IThemeDTO::fromTheme);
  }

  public List<IThemeDTO> getThemes() {
    return this.themeCatalog.getThemes().stream().map(IThemeDTO::fromTheme).toList();
  }

  public List<IThemeDTO> getByYear(String year, Long mastersId) throws InvalidFieldException, NotFoundException {
    try {
      return this.listThemesHandler.listThemesInYear(year, mastersId).stream().map(IThemeDTO::fromTheme).toList();
    } catch (InvalidYearException e) {
      throw new InvalidFieldException("Invalid year specified!", e);
    } catch (MastersDegreeNotFoundException e) {
      throw new NotFoundException("Masters degree not found!", e);
    }
  }

  public void assignTheme(Long themeId, Long studentId) throws AlreadyExistsException, NotFoundException {
    try {
      this.adminAssignThemeHandler.assignTheme(themeId, studentId);
    } catch (ThemeNotFoundException e) {
      throw new NotFoundException("Theme with id " + themeId + " was not found!", e);
    } catch (StudentNotFoundException e) {
      throw new NotFoundException("Student with id " + studentId + " was not found!", e);
    } catch (StudentAlreadyHasThesisException e) {
      throw new AlreadyExistsException("Student already has a thesis!", e);
    } catch (ThemeTakenException e) {
      throw new AlreadyExistsException("The theme is already taken!", e);
    }
  }
}
