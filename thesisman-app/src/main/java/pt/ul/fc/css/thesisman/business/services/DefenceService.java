package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.DefenceCatalog;
import pt.ul.fc.css.thesisman.business.catalogs.SubmissionCatalog;
import pt.ul.fc.css.thesisman.business.entities.Submission;
import pt.ul.fc.css.thesisman.business.enums.DefenceType;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.handlers.GradeFinalDefenceHandler;
import pt.ul.fc.css.thesisman.business.handlers.GradeProposalDefenceHandler;
import pt.ul.fc.css.thesisman.business.handlers.ScheduleFinalDefenceHandler;
import pt.ul.fc.css.thesisman.business.handlers.ScheduleProposalDefenceHandler;
import pt.ul.fc.css.thesisman.business.services.dtos.DefenceDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;
import pt.ul.fc.css.thesisman.business.services.exceptions.SchedulingException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DefenceService {

  @Autowired private ScheduleProposalDefenceHandler scheduleProposeDefenceHandler;
  @Autowired private ScheduleFinalDefenceHandler scheduleFinalDefenceHandler;
  @Autowired private SubmissionService submissionService;
  @Autowired private GradeProposalDefenceHandler gradeProposeDefenceHandler;
  @Autowired private GradeFinalDefenceHandler gradeFinalDefenceHandler;
  @Autowired private DefenceCatalog defenceCatalog;
  @Autowired private SubmissionCatalog submissionCatalog;

  public Optional<DefenceDTO> getById(Long defenceId) {
    return this.defenceCatalog.getById(defenceId).map(DefenceDTO::fromDefence);
  }

  public List<DefenceDTO> listParticipatingDefences(Long teacherId) {
    return this.defenceCatalog.listParticipatingDefences(teacherId).stream().map(DefenceDTO::fromDefence).toList();
  }

  public void gradeFinalDefence(Long defenceId, Float grade) throws NotFoundException, InvalidFieldException {
    try {
      this.gradeFinalDefenceHandler.gradeFinalDefense(defenceId, grade);
    } catch (InvalidGradeException e) {
      throw new InvalidFieldException("Invalid grade submitted!", e);
    } catch (DefenceNotFoundException e) {
      throw new NotFoundException("Defence with id " + defenceId + " was not found!", e);
    } catch (SubmissionNotFoundException e) {
      throw new NotFoundException("Submission from defence with id " + defenceId + " was not found!", e);
    } catch (InvalidSubmissionTypeException e) {
      throw new InvalidFieldException("The specified defence is not a final defence!", e);
    }
  }

  public void gradeProposalDefence(Long defenceId, Float grade) throws NotFoundException, InvalidFieldException {
    try {
      this.gradeProposeDefenceHandler.gradeProposalDefence(defenceId, grade);
    } catch (InvalidGradeException e) {
      throw new InvalidFieldException("Invalid grade submitted!", e);
    } catch (DefenceNotFoundException e) {
      throw new NotFoundException("Defence with id " + defenceId + " was not found!", e);
    } catch (SubmissionNotFoundException e) {
      throw new NotFoundException("Submission from defence with id " + defenceId + " was not found!", e);
    } catch (InvalidSubmissionTypeException e) {
      throw new InvalidFieldException("The specified defence is not a proposal defence!", e);
    }
  }

  public void scheduleDefence(Long submissionId, String type, LocalDateTime date, Long arguerId, Long classRoomId, Long presidentId) throws NotFoundException, AlreadyExistsException, InvalidFieldException, SchedulingException {
    DefenceType defenceType;
    try {
      defenceType = DefenceType.valueOf(type);
    } catch (IllegalArgumentException e) {
      throw new InvalidFieldException("Invalid defence type!", e);
    }
    Optional<Submission> submissionOpt = this.submissionCatalog.getById(submissionId);
    if (submissionOpt.isEmpty())
      throw new NotFoundException("Submission not found!");
    Submission submission = submissionOpt.get();
    try {
      switch (submission.getType()) {
        case FINAL -> {
          switch (defenceType) {
            case IN_PERSON -> this.scheduleFinalDefenceHandler.scheduleInPersonFinalDefence(submissionId, date, arguerId, classRoomId, presidentId);
            case REMOTE -> this.scheduleFinalDefenceHandler.scheduleRemoteFinalDefence(submissionId, date, arguerId, presidentId);
          }
        }
        case PROPOSAL -> {
          switch (defenceType) {
            case IN_PERSON -> this.scheduleProposeDefenceHandler.scheduleInPersonProposalDefence(submissionId, date, arguerId, classRoomId);
            case REMOTE -> this.scheduleProposeDefenceHandler.scheduleRemoteProposalDefence(submissionId, date, arguerId);
          }
        }
      }
    } catch (TeacherNotFoundException e) {
      throw new NotFoundException("Teacher was not found!", e);
    } catch (DefenceAlreadyScheduledException e) {
      throw new AlreadyExistsException("There is already a defence scheduled!", e);
    } catch (InvalidDateException e) {
      throw new InvalidFieldException("Invalid date specified!", e);
    } catch (SubmissionNotFoundException e) {
      throw new NotFoundException("Submission with id " + submissionId + " was not found!", e);
    } catch (ClassRoomNotFoundException e) {
      throw new NotFoundException("ClassRoom with id " + classRoomId + " was not found!", e);
    } catch (InvalidJuriException e) {
      throw new NotFoundException("The juri composition is invalid!", e);
    } catch (ThesisNotFoundException e) {
      throw new NotFoundException("Thesis for submission with id " + submissionId + " was not found!", e);
    } catch (RoomOccupiedException e) {
      throw new SchedulingException("Room is occupied in that date!");
    }
  }
}
