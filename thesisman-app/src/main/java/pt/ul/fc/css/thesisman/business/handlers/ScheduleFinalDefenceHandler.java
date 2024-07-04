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
public class ScheduleFinalDefenceHandler {

  private static final int FINAL_DEFENCE_DURATION_MINUTES = 90;

  @Autowired private DefenceRepository defenceRepository;
  @Autowired private SubmissionRepository submissionRepository;
  @Autowired private TeacherRepository teacherRepository;
  @Autowired private ClassRoomRepository classRoomRepository;
  @Autowired private ThesisRepository thesisRepository;
  @Autowired private DefenceCatalog defenceCatalog;
  @Autowired private ScheduleValidator scheduleValidator;

  @Transactional
  public void scheduleRemoteFinalDefence(Long submissionId, LocalDateTime date, Long arguerId, Long presidentId) throws InvalidDateException, SubmissionNotFoundException, TeacherNotFoundException, ThesisNotFoundException, InvalidJuriException, DefenceAlreadyScheduledException {
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
    Optional<Teacher> presidentOpt = this.teacherRepository.findById(presidentId);
    if (presidentOpt.isEmpty()) {
      throw new TeacherNotFoundException();
    }
    Teacher president = presidentOpt.get();
    Defence defence = new Defence(DefenceType.REMOTE);
    defence.setArguer(arguer);
    defence.setDateTime(date);
    defence.setDuration(FINAL_DEFENCE_DURATION_MINUTES);
    defence.setPresident(president);
    this.defenceRepository.save(defence);
    submission.setDefence(defence);
    this.submissionRepository.save(submission);
  }

  @Transactional
  public void scheduleInPersonFinalDefence(Long submissionId, LocalDateTime date, Long arguerId, Long classroomId, Long presidentId) throws InvalidDateException, SubmissionNotFoundException, TeacherNotFoundException, ThesisNotFoundException, InvalidJuriException, DefenceAlreadyScheduledException, ClassRoomNotFoundException, RoomOccupiedException {
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
    ClassRoom classRoom = classRoomOpt.get();
    Optional<Teacher> presidentOpt = this.teacherRepository.findById(presidentId);
    if (presidentOpt.isEmpty()) {
      throw new TeacherNotFoundException();
    }
    List<Defence> defences = defenceCatalog.getByClassRoomId(classroomId);
    LocalDateTime start = date;
    LocalDateTime end = date.plusMinutes(FINAL_DEFENCE_DURATION_MINUTES);
    if (scheduleValidator.hasConflict(start, end, defences)) {
      throw new RoomOccupiedException();
    }
    Teacher president = presidentOpt.get();
    Defence defence = new Defence(DefenceType.REMOTE);
    defence.setArguer(arguer);
    defence.setDateTime(date);
    defence.setDuration(FINAL_DEFENCE_DURATION_MINUTES);
    defence.setClassRoom(classRoom);
    defence.setPresident(president);
    this.defenceRepository.save(defence);
    submission.setDefence(defence);
    this.submissionRepository.save(submission);
  }
}
