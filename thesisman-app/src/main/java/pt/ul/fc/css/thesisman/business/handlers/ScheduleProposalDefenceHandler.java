package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.catalogs.DefenceCatalog;
import pt.ul.fc.css.thesisman.business.entities.*;
import pt.ul.fc.css.thesisman.business.enums.DefenceType;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.repositories.*;
import pt.ul.fc.css.thesisman.business.validators.ScheduleValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduleProposalDefenceHandler {

  private static final int PROPOSAL_DEFENCE_DURATION_MINUTES = 60;

  @Autowired private DefenceRepository defenceRepository;
  @Autowired private SubmissionRepository submissionRepository;
  @Autowired private TeacherRepository teacherRepository;
  @Autowired private ClassRoomRepository classRoomRepository;
  @Autowired private ThesisRepository thesisRepository;
  @Autowired private DefenceCatalog defenceCatalog;
  @Autowired private ScheduleValidator scheduleValidator;

  @Transactional
  public void scheduleRemoteProposalDefence(Long submissionId, LocalDateTime date, Long arguerId) throws InvalidDateException, SubmissionNotFoundException, TeacherNotFoundException, ThesisNotFoundException, InvalidJuriException, DefenceAlreadyScheduledException {
    if (date == null || date.isBefore(LocalDateTime.now())) {
      throw new InvalidDateException();
    }
    Optional<Submission> submissionOpt = this.submissionRepository.findById(submissionId);
    if (submissionOpt.isEmpty()) {
      throw new SubmissionNotFoundException();
    }
    Submission submission = submissionOpt.get();
    Optional<Teacher> arguerOpt = this.teacherRepository.findById(arguerId);
    if (arguerOpt.isEmpty()) {
      throw new TeacherNotFoundException();
    }
    Teacher arguer = arguerOpt.get();
    Optional<Thesis> thesisOpt = this.thesisRepository.findThesisBySubmissionId(submissionId);
    if (thesisOpt.isEmpty()) {
      throw new ThesisNotFoundException();
    }
    Thesis thesis = thesisOpt.get();
    if (thesis.getTheme().getAdvisor().getId().equals(arguer.getId())) {
      throw new InvalidJuriException();
    }
    if (submission.getDefence() != null) {
      throw new DefenceAlreadyScheduledException();
    }
    Defence defence = new Defence(DefenceType.REMOTE);
    defence.setArguer(arguer);
    defence.setDateTime(date);
    defence.setDuration(PROPOSAL_DEFENCE_DURATION_MINUTES);
    this.defenceRepository.save(defence);
    submission.setDefence(defence);
    this.submissionRepository.save(submission);
  }

  @Transactional
  public void scheduleInPersonProposalDefence(Long submissionId, LocalDateTime date, Long arguerId, Long classroomId) throws InvalidDateException, SubmissionNotFoundException, TeacherNotFoundException, ThesisNotFoundException, InvalidJuriException, DefenceAlreadyScheduledException, ClassRoomNotFoundException, RoomOccupiedException {
    if (date == null || date.isBefore(LocalDateTime.now())) {
      throw new InvalidDateException();
    }
    Optional<Submission> submissionOpt = this.submissionRepository.findById(submissionId);
    if (submissionOpt.isEmpty()) {
      throw new SubmissionNotFoundException();
    }
    Submission submission = submissionOpt.get();
    Optional<Teacher> arguerOpt = this.teacherRepository.findById(arguerId);
    if (arguerOpt.isEmpty()) {
      throw new TeacherNotFoundException();
    }
    Teacher arguer = arguerOpt.get();
    Optional<Thesis> thesisOpt = this.thesisRepository.findThesisBySubmissionId(submissionId);
    if (thesisOpt.isEmpty()) {
      throw new ThesisNotFoundException();
    }
    Thesis thesis = thesisOpt.get();
    if (thesis.getTheme().getAdvisor().getId().equals(arguer.getId())) {
      throw new InvalidJuriException();
    }
    if (submission.getDefence() != null) {
      throw new DefenceAlreadyScheduledException();
    }
    Optional<ClassRoom> classRoomOpt = this.classRoomRepository.findById(classroomId);
    if (classRoomOpt.isEmpty()) {
      throw new ClassRoomNotFoundException();
    }
    List<Defence> defences = defenceCatalog.getByClassRoomId(classroomId);
    LocalDateTime start = date;
    LocalDateTime end = date.plusMinutes(PROPOSAL_DEFENCE_DURATION_MINUTES);
    if (scheduleValidator.hasConflict(start, end, defences)) {
      throw new RoomOccupiedException();
    }
    ClassRoom classRoom = classRoomOpt.get();
    Defence defence = new Defence(DefenceType.REMOTE);
    defence.setArguer(arguer);
    defence.setDateTime(date);
    defence.setDuration(PROPOSAL_DEFENCE_DURATION_MINUTES);
    defence.setClassRoom(classRoom);
    this.defenceRepository.save(defence);
    submission.setDefence(defence);
    this.submissionRepository.save(submission);
  }
}
