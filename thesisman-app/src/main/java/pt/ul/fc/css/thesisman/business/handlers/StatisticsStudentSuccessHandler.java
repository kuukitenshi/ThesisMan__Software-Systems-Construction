package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.entities.Defence;
import pt.ul.fc.css.thesisman.business.entities.Submission;
import pt.ul.fc.css.thesisman.business.enums.SubmissionType;
import pt.ul.fc.css.thesisman.business.objects.Statistics;
import pt.ul.fc.css.thesisman.business.repositories.DefenceRepository;
import pt.ul.fc.css.thesisman.business.repositories.StudentRepository;
import pt.ul.fc.css.thesisman.business.repositories.SubmissionRepository;

import java.util.Optional;

@Component
public class StatisticsStudentSuccessHandler {

  @Autowired private StudentRepository studentRepository;
  @Autowired private DefenceRepository defenceRepository;
  @Autowired private SubmissionRepository submissionRepository;

  @Transactional
  public Statistics getStatistics() {
    Statistics statistics = new Statistics();
    statistics.setTotalStudents(studentRepository.count());
    long totalApproved = 0;
    long totalUnapproved = 0;
    double grades = 0;
    for (Defence defence : defenceRepository.findAll()) {
      if (defence.getGrade() == null) {
        continue;
      }
      Optional<Submission> submissionOpt = submissionRepository.findSubmissionByDefenceId(defence.getId());
      if (submissionOpt.isEmpty()) {
        continue;
      }
      Submission submission = submissionOpt.get();
      if (submission.getType() == SubmissionType.FINAL) {
        grades += defence.getGrade();
        if (defence.getGrade() >= 9.5)
          totalApproved++;
        else
          totalUnapproved++;
      }
    }
    statistics.setTotalApproved(totalApproved);
    statistics.setTotalUnapproved(totalUnapproved);
    long totalGraded = totalApproved + totalUnapproved;
    if (totalGraded > 0) {
      double mean = grades / totalGraded;
      mean = Math.round(mean * 100) / 100.0;
      statistics.setMeanGrade(mean);
    }
    return statistics;
  }
}
